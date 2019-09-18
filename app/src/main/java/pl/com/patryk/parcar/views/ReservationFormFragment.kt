package pl.com.patryk.parcar.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import pl.com.patryk.parcar.R
import pl.com.patryk.parcar.databinding.ReservationFormFragmentBinding
import pl.com.patryk.parcar.models.ReservationFormViewModel
import pl.com.patryk.parcar.utilites.InjectorUtils
import pl.com.patryk.parcar.views.adapters.ReservationFormAdapter

class ReservationFormFragment : Fragment() {



    private val viewModel: ReservationFormViewModel by viewModels {
        InjectorUtils.provideReservationFormViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ReservationFormFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root


        val adapter = ReservationFormAdapter()
        binding.recycleViewReservationForms.adapter = adapter
        subscribeUi(adapter)

        binding.addButton.setOnClickListener{
            findNavController().navigate(R.id.action_reservationFormFragment_to_addReservationFormDialogFragment3)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

    private fun subscribeUi(adapter: ReservationFormAdapter) {
        viewModel.reservationForm.observe(viewLifecycleOwner) {
            /**
             *  Plant may return null, but the [observe] extension function assumes it will not be null.
             *  So there will be a warning（Condition `plants != null` is always `true`） here.
             *  I am not sure if the database return data type should be defined as nullable, Such as `LiveData<List<Plant>?>` .
             */
            if (it != null) adapter.submitList(it)
        }
    }

}