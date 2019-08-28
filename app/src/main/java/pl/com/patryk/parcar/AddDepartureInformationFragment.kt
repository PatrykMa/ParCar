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




class AddDepartureInformationFragment : Fragment() {

    private val viewModel:AddDepartureViewModel by viewModels {
        InjectorUtils.provideAddDepartureViewModelFactory(requireContext())
    }
    lateinit var binding:FragmentAddDepartureInformationBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       // viewModel = ViewModelProviders.of(this).get(AddDepartureViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentAddDepartureInformationBinding>(inflater,R.layout.fragment_add_departure_information, container, false)
        setAdditionalInfoListener()
        setPlateListener()
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
    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()

    }




}
