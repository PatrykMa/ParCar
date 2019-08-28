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
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import pl.com.patryk.parcar.utilites.InjectorUtils
import android.widget.AdapterView.OnItemSelectedListener




class AddDeparturePaymentFragment : Fragment() {


    private val viewModel:AddDepartureViewModel by viewModels {
        InjectorUtils.provideAddDepartureViewModelFactory(requireContext())
    }
    lateinit var binding:FragmentAddDeparturePaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //subscribeUI()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // text.addTextChangedListener(watcher)
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentAddDeparturePaymentBinding>(inflater,R.layout.fragment_add_departure_payment, container, false)
        //ToDO zastąpić
        //binding.departure = viewModel.departure.value
        subscribeUI()
        setPriceChangeListener()
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

    fun setPriceChangeListener()
    {
        binding.editTextPrice.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                viewModel.price = s.toString().toInt()}

            override fun beforeTextChanged( s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,before: Int, count: Int) {

            }
        })
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
