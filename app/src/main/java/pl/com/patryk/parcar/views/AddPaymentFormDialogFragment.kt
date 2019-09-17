package pl.com.patryk.parcar.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import pl.com.patryk.parcar.R
import pl.com.patryk.parcar.databinding.PaymentFormAddDialogFragmentBinding
import pl.com.patryk.parcar.databinding.PaymentFormDialogFragmentBinding
import pl.com.patryk.parcar.models.PaymentFormDialogFragmentViewModel
import pl.com.patryk.parcar.utilites.InjectorUtils

class AddPaymentFormDialogFragment : DialogFragment() {
    private val args: PaymentFormDialogFragmentArgs by navArgs()
    private val viewModel: PaymentFormDialogFragmentViewModel by viewModels {
        InjectorUtils.providePaymentFormDialogFragmentViewModel(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<PaymentFormAddDialogFragmentBinding>(inflater,
            R.layout.payment_form_add_dialog_fragment, container, false)
        binding.buttonAdd.setOnClickListener {
            viewModel.add(binding.editTextName.text.toString())
            dismiss()
        }

        binding.buttonCancel.setOnClickListener {
            dismiss()
        }
        return binding.root//inflater.inflate(R.layout.departure_dialog_fragment, container, false)
    }
}