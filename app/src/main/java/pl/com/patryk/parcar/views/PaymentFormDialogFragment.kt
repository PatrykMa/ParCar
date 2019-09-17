package pl.com.patryk.parcar.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import pl.com.patryk.parcar.R
import pl.com.patryk.parcar.databinding.DepartureDialogFragmentBinding
import pl.com.patryk.parcar.databinding.PaymentFormDialogFragmentBinding
import pl.com.patryk.parcar.models.PaymentFormDialogFragmentViewModel
import pl.com.patryk.parcar.models.PaymentFormViewModel
import pl.com.patryk.parcar.utilites.InjectorUtils

class PaymentFormDialogFragment:DialogFragment() {
    private val args: PaymentFormDialogFragmentArgs by navArgs()
    private val viewModel: PaymentFormDialogFragmentViewModel by viewModels {
        InjectorUtils.providePaymentFormDialogFragmentViewModel(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<PaymentFormDialogFragmentBinding>(inflater,
            R.layout.payment_form_dialog_fragment, container, false)
        binding.name =args.name
        binding.materialTextButtonDelete.setOnClickListener {

            viewModel.delete(args.id.toLong())
            dismiss()
        }
        binding.materialTextButtonEdit.setOnClickListener {
            //ToDO
        }
        binding.materialTextButtonCancel.setOnClickListener {
            dismiss()
        }
        // Do all the stuff to initialize your custom view

        return binding.root//inflater.inflate(R.layout.departure_dialog_fragment, container, false)
    }
}