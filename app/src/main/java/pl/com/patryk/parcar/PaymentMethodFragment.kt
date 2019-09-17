package pl.com.patryk.parcar

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import pl.com.patryk.parcar.databinding.PaymentMethodFragmentBinding
import pl.com.patryk.parcar.models.AddDepartureViewModel
import pl.com.patryk.parcar.models.PaymentFormViewModel
import pl.com.patryk.parcar.models.PaymentMethodViewModel
import pl.com.patryk.parcar.utilites.InjectorUtils
import pl.com.patryk.parcar.views.adapters.PaymentFormAdapter


class PaymentMethodFragment : Fragment() {



    private val viewModel: PaymentFormViewModel by viewModels {
        InjectorUtils.providePaymentFormViewModel(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = PaymentMethodFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root


        val adapter = PaymentFormAdapter()
        binding.recycleViewPaymentForms.adapter = adapter
        subscribeUi(adapter)

        binding.addButton.setOnClickListener{
            findNavController().navigate(R.id.action_paymentMethodFragment_to_addPaymentFormDialogFragment)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

    private fun subscribeUi(adapter: PaymentFormAdapter) {
        viewModel.paymentForms.observe(viewLifecycleOwner) { paymentForms ->
            /**
             *  Plant may return null, but the [observe] extension function assumes it will not be null.
             *  So there will be a warning（Condition `plants != null` is always `true`） here.
             *  I am not sure if the database return data type should be defined as nullable, Such as `LiveData<List<Plant>?>` .
             */
            if (paymentForms != null) adapter.submitList(paymentForms)
        }
    }

}
