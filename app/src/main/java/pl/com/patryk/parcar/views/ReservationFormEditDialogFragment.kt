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
import pl.com.patryk.parcar.databinding.ReservationFormEditDialogFragmentBinding
import pl.com.patryk.parcar.models.ReservationFormViewModel
import pl.com.patryk.parcar.utilites.InjectorUtils

class ReservationFormEditDialogFragment : DialogFragment() {
    private val args: ReservationFormEditDialogFragmentArgs by navArgs()
    private val viewModel: ReservationFormViewModel by viewModels {
        InjectorUtils.provideReservationFormViewModelFactory(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<ReservationFormEditDialogFragmentBinding>(inflater,
            R.layout.reservation_form_edit_dialog_fragment, container, false)
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