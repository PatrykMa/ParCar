package pl.com.patryk.parcar

import android.content.Context
import android.os.Bundle
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import pl.com.patryk.parcar.databinding.FragmentAddDepartureInformationBinding
import pl.com.patryk.parcar.databinding.FragmentAddDeparturePaymentBinding
import pl.com.patryk.parcar.databinding.FragmentAddDepartureTimeBinding
import pl.com.patryk.parcar.models.AddDepartureViewModel
import pl.com.patryk.parcar.utilites.InjectorUtils
import java.text.SimpleDateFormat
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import android.text.Editable
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.observe
import android.text.InputFilter




class AddDepartureInformationFragment : Fragment() {

    private val viewModel:AddDepartureViewModel by viewModels {
        InjectorUtils.provideAddDepartureViewModelFactory(requireContext())
    }
    lateinit var binding:FragmentAddDepartureInformationBinding


    override fun onResume() {
        super.onResume()
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        binding.editTextPlate.requestFocus()
        imm.showSoftInput(binding.editTextPlate, InputMethodManager.SHOW_IMPLICIT)
        //imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    override fun onPause() {
        super.onPause()
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.hideSoftInputFromWindow(binding.editTextPlate.windowToken, 0)
        imm!!.hideSoftInputFromWindow(binding.textViewAdditionalInformation.windowToken, 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentAddDepartureInformationBinding>(inflater,R.layout.fragment_add_departure_information, container, false)
        setAdditionalInfoListener()
        setPlateListener()
        setDepartureObserver()
        binding.editTextPlate.filters = arrayOf<InputFilter>(InputFilter.AllCaps())
        return binding.root
        // Inflate the layout for this fragment
        //binding = DataBindingUtil.inflate<FragmentAddDepartureTimeBinding>(inflater,R.layout.fragment_add_departure_information, container, false)
        //return inflater.inflate(R.layout.fragment_add_departure_information, container, false)

    }


    fun setPlateListener()
    {
        binding.editTextPlate.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged( s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,before: Int, count: Int) {
                viewModel.plate =s.toString()
            }
        })
    }

    fun setAdditionalInfoListener(){
        binding.textViewAdditionalInformation.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                viewModel.additionalInformation = s.toString()}

            override fun beforeTextChanged( s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,before: Int, count: Int) {

            }
        })

    }
    private fun setDepartureObserver()
    {
        viewModel.departure.observe(viewLifecycleOwner){
            binding.editTextPlate.setText(it.plate)
            binding.textViewAdditionalInformation.setText(it.additionalInformatioin)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()

    }




}
