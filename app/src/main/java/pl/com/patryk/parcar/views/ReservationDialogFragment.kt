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
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import pl.com.patryk.parcar.R
import pl.com.patryk.parcar.databinding.ReservationDialogFragmentBinding
import pl.com.patryk.parcar.models.ReservationDialogViewModel
import pl.com.patryk.parcar.utilites.InjectorUtils

class ReservationDialogFragment : DialogFragment() {
    private val args: ReservationDialogFragmentArgs by navArgs()
    private val viewModel : ReservationDialogViewModel by viewModels{
        InjectorUtils.provideReservationDialogViewModelFactory(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<ReservationDialogFragmentBinding>(inflater,
            R.layout.reservation_dialog_fragment, container, false)
        binding.date =args.date
        binding.materialTextButtonDelete.setOnClickListener {

            viewModel.delete(args.id)
            dismiss()
        }
        binding.materialTextButtonArrive.setOnClickListener {
            doAsync {
                val res =viewModel.getReservation(args.id)
                uiThread {
                    val direction = ReservationDialogFragmentDirections
                        .actionReservationDialogFragmentToAddDepartureFragment(-1, res.arrivalDate,res.isPaid)
                    findNavController().navigate(direction)
                }
            }
        }
        binding.materialTextButtonEdit.setOnClickListener {
            val direction = ReservationDialogFragmentDirections.actionReservationDialogFragmentToAddReservationFragment(args.id)
            findNavController().navigate(direction)
        }
        binding.materialTextButtonCancel.setOnClickListener {
            dismiss()
        }
        // Do all the stuff to initialize your custom view

        return binding.root//inflater.inflate(R.layout.departure_dialog_fragment, container, false)
    }
}