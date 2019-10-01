package pl.com.patryk.parcar

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.jetbrains.anko.sdk27.coroutines.onCheckedChange
import org.jetbrains.anko.sdk27.coroutines.onItemSelectedListener
import pl.com.patryk.parcar.databinding.AddReservationFragmentBinding
import pl.com.patryk.parcar.models.AddReservationViewModel
import pl.com.patryk.parcar.utilites.InjectorUtils
import java.lang.Exception
import java.util.*


class AddReservationFragment : Fragment(),DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {


    private val args : AddReservationFragmentArgs by navArgs()
    private  val viewModel: AddReservationViewModel by viewModels {
        InjectorUtils.provideAddReservationViewModelFactory(requireContext())
    }
    lateinit var binding:AddReservationFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<AddReservationFragmentBinding>(inflater,R.layout.add_reservation_fragment, container, false)

        if(args.id >= 0)
        {
            viewModel.setReservation(args.id)
        }
        obserwe()
        binding.floatingActionButtonAdd.setOnClickListener {
            onSave()
            findNavController().navigate(R.id.action_addReservationFragment_to_reservationFragment)
        }
        setListener()
        return binding.root
    }




    private fun setListener()
    {
        binding.setOnFromDateClick{
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = viewModel.reservation.value!!.arrivalDate
            var y = calendar.get(Calendar.YEAR)
            val m = calendar.get(Calendar.MONTH)
            val d = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(
                context, this, y, m, d
            ).show()
        }
        binding.setOnFromTimeClick {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = viewModel.reservation.value!!.arrivalDate
            TimePickerDialog(context,this,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show()
        }

        binding.spinnerReservationForms.onItemSelectedListener {
            viewModel.reservation.value?.reservationForm = try{binding.spinnerReservationForms.selectedItem.toString()}
            catch(e:Exception){""}
        }

        binding.switch2.onCheckedChange { buttonView, isChecked ->
            viewModel.reservation.value?.isPaid = isChecked
        }

    }


    private fun onSave(){
        viewModel.save()
    }

    private fun obserwe()
    {
        viewModel.reservation.observe(viewLifecycleOwner){
            binding.reservation = it
            binding.invalidateAll()
            Log.d("rese", it.arrivalDate.toString())
        }

        viewModel.reservationForm.observe(viewLifecycleOwner){
            val dataAdapter = ArrayAdapter<String>(
                context,
                android.R.layout.simple_spinner_item, it
            )
            binding.spinnerReservationForms.adapter = dataAdapter
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
            calendar.timeInMillis = viewModel.reservation.value!!.arrivalDate
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,month)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            viewModel.reservation.value!!.arrivalDate = calendar.time.time
        binding.invalidateAll()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val calendar = Calendar.getInstance()
            calendar.timeInMillis = viewModel.reservation.value!!.arrivalDate
            calendar.set(Calendar.HOUR_OF_DAY,hourOfDay)
            calendar.set(Calendar.MINUTE,minute)
            viewModel.reservation.value!!.arrivalDate = calendar.time.time
        binding.invalidateAll()
    }

}
