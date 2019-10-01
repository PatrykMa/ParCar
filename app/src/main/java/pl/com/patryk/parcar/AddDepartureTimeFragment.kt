package pl.com.patryk.parcar

import android.app.DatePickerDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import kotlinx.android.synthetic.main.fragment_add_departure_time.*
import android.R.attr.startYear
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import pl.com.patryk.parcar.databinding.FragmentAddDepartureTimeBinding
import pl.com.patryk.parcar.models.AddDepartureViewModel

import java.text.SimpleDateFormat
import java.time.Month
import java.util.*
import android.R.attr.startYear
import android.app.TimePickerDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.google.android.material.textview.MaterialTextView
import pl.com.patryk.parcar.utilites.InjectorUtils


class AddDepartureTimeFragment : Fragment(),DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {

    private val viewModel:AddDepartureViewModel by viewModels {
        InjectorUtils.provideAddDepartureViewModelFactory(requireContext())
    }
    private var isLastFrom = true
    lateinit var binding:FragmentAddDepartureTimeBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentAddDepartureTimeBinding>(inflater,R.layout.fragment_add_departure_time, container, false)
        binding.model = viewModel
        setListener()
        setDepartureObserver()
        return binding.root
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        if (isLastFrom) {
            calendar.timeInMillis = viewModel.from
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,month)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            viewModel.from = calendar.timeInMillis
        }
        else{
            calendar.timeInMillis = viewModel.to
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,month)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            viewModel.to = calendar.timeInMillis
        }
        binding.invalidateAll()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        if (isLastFrom) {
            calendar.timeInMillis = viewModel.from
            calendar.set(Calendar.HOUR_OF_DAY,hourOfDay)
            calendar.set(Calendar.MINUTE,minute)
            viewModel.from = calendar.timeInMillis
        }
        else{
            calendar.timeInMillis = viewModel.to
            calendar.set(Calendar.HOUR_OF_DAY,hourOfDay)
            calendar.set(Calendar.MINUTE,minute)
            viewModel.to = calendar.timeInMillis
        }

        binding.invalidateAll()
    }

    /*private fun display()
    {
        val dateFormat = SimpleDateFormat("yy:MM:dd")
        val timeFormat = SimpleDateFormat("hh:mm")

        binding.fromDate = dateFormat.format(viewModel._departure.value?.from)
        binding.fromTime = timeFormat.format(viewModel._departure.value?.from)

        binding.toDate = dateFormat.format(viewModel._departure.value?.to)
        binding.toTime = timeFormat.format(viewModel._departure.value?.to)
    }*/
    private fun setListener()
    {

        binding.onFromDateClik = View.OnClickListener {
            isLastFrom = true
            val calendar = Calendar.getInstance()
            calendar.time.time = viewModel.from
            DatePickerDialog(
                context, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        binding.onFromTimeClik = View.OnClickListener {
            isLastFrom = true
            val calendar = Calendar.getInstance()
            calendar.time.time = viewModel.from
            TimePickerDialog(context,this,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show()
        }
        binding.onToDateClik = View.OnClickListener {
            isLastFrom = false
            val calendar = Calendar.getInstance()
            calendar.time.time = viewModel.to
            DatePickerDialog(
                context, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.onToTimeClik = View.OnClickListener {
            isLastFrom = false
            val calendar = Calendar.getInstance()
            calendar.time.time = viewModel.to
            TimePickerDialog(context,this,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show()
        }
    }
    private fun setDepartureObserver()
    {
        viewModel.departure.observe(viewLifecycleOwner){departure ->
            binding.invalidateAll()
        }
    }


}
