package pl.com.patryk.parcar.views

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import pl.com.patryk.parcar.DepartureFragmentDirections
import pl.com.patryk.parcar.R
import pl.com.patryk.parcar.databinding.DepartureDialogFragmentBinding
import pl.com.patryk.parcar.databinding.FragmentAddDepartureInformationBinding
import pl.com.patryk.parcar.models.AddDepartureViewModel
import pl.com.patryk.parcar.models.DepartureOptionsDialogFragmentViewModel
import pl.com.patryk.parcar.utilites.InjectorUtils


class DepartureOptionsDialog: DialogFragment() {
    private val args: DepartureOptionsDialogArgs by navArgs()
    private val viewModel: DepartureOptionsDialogFragmentViewModel by viewModels {
        InjectorUtils.provideDepartureOptionsDialogFragmentFactory(requireContext())
    }
    lateinit var binding:DepartureDialogFragmentBinding
    /*override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.HORIZONTAL
        val editButton = Button(context)
        editButton.setOnClickListener{

        }

        val arriveButton = Button(context)
        arriveButton.setOnClickListener {  }

        layout.addView(editButton)
        layout.addView(arriveButton)

        val alert = AlertDialog.Builder(context!!)
            .setIcon(R.drawable.ic_menu_share)
            .setTitle(args.departureId.toString())
            .setView(layout)
            .setCancelable(true)

        //return alert.create()
        val v = inflater.inflate(R.layout.fragment_my_custom_dialog, container, false)
    }*/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate<DepartureDialogFragmentBinding>(inflater,R.layout.departure_dialog_fragment, container, false)
        binding.plate =args.plate
        binding.materialTextButtonDelete.setOnClickListener {
            viewModel.deleteDeparture(args.departureId)
            dismiss()
        }
        binding.materialTextButtonEdit.setOnClickListener {
            val direction = DepartureOptionsDialogDirections.actionDepartureOptionsDialogToAddDepartureFragment(args.departureId)
            findNavController().navigate(direction)
        }
        binding.materialTextButtonCancel.setOnClickListener {
            dismiss()
        }
        // Do all the stuff to initialize your custom view

        return binding.root//inflater.inflate(R.layout.departure_dialog_fragment, container, false)
    }
}