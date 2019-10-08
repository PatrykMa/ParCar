package pl.com.patryk.parcar

import android.content.Context
import android.os.Bundle
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import pl.com.patryk.parcar.databinding.FragmentAddDeparturePaymentBinding
import pl.com.patryk.parcar.models.AddDepartureViewModel
import android.text.Editable
import android.util.Log
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import pl.com.patryk.parcar.utilites.InjectorUtils
import android.widget.AdapterView.OnItemSelectedListener
import android.view.inputmethod.InputMethodManager.HIDE_IMPLICIT_ONLY
import android.view.inputmethod.InputMethodSubtype
import androidx.core.content.ContextCompat.getSystemService
import java.lang.Exception


class AddDeparturePaymentFragment : Fragment() {


    private val viewModel:AddDepartureViewModel by viewModels {
        InjectorUtils.provideAddDepartureViewModelFactory(requireContext())
    }
    lateinit var binding:FragmentAddDeparturePaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //subscribeUI()

    }

    override fun onResume() {
        super.onResume()
        setDepartureObserver()
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        binding.editTextPrice.requestFocus()
        //imm.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        imm.showSoftInput(binding.editTextPrice, InputMethodManager.SHOW_FORCED)
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    override fun onPause() {
        super.onPause()
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.hideSoftInputFromWindow(binding.editTextPrice.windowToken, 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // text.addTextChangedListener(watcher)
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentAddDeparturePaymentBinding>(inflater,R.layout.fragment_add_departure_payment, container, false)
        //ToDO zastąpić
        //binding._departure = viewModel._departure.value
        subscribeUI()
        setPriceChangeListener()
        setSpinnerListener()
        setIsPayListener()

        return binding.root

    }

    private fun subscribeUI()
    {
        viewModel.paymentForms.observe(viewLifecycleOwner){ paymentFormd ->
            val dataAdapter = ArrayAdapter<String>(
                context,
                android.R.layout.simple_spinner_item, paymentFormd
            )
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerPaymentFormList.adapter = dataAdapter
        }
    }

    fun setSpinnerListener()
    {

        binding.spinnerPaymentFormList.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(parentView: AdapterView<*>,selectedItemView: View,position: Int,id: Long ) {
                    viewModel.paymentForm = binding.spinnerPaymentFormList.selectedItem.toString()
                }

                override fun onNothingSelected(parentView: AdapterView<*>) {
                    // your code here
                }

            }
    }

    fun setIsPayListener()
    {
        binding.switchPay.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.departure.value!!.isPaid = isChecked
        }
    }

    fun setPriceChangeListener()
    {
        binding.editTextPrice.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                viewModel.price = try{s.toString().toInt()} catch (e:Exception){0}}

            override fun beforeTextChanged( s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,before: Int, count: Int) {

            }
        })
    }

    private fun setDepartureObserver()
    {
        viewModel.departure.observe(viewLifecycleOwner){
            binding.price = it.price!!.toString()
            binding.switchPay.isChecked = it.isPaid
            binding.spinnerPaymentFormList.setSelection(viewModel.paymentForms.value!!.indexOf(it.paymentForm))
            binding.isPay = it.isPaid
            Log.d("price",it.isPaid.toString())
            binding.invalidateAll()
        }
        var x =0
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()

    }

    private fun setListener()
    {

    }

}
