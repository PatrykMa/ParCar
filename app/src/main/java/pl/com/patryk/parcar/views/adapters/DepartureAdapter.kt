package pl.com.patryk.parcar.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import pl.com.patryk.parcar.AddDepartureFragment
import pl.com.patryk.parcar.AddDepartureFragmentDirections
import pl.com.patryk.parcar.DepartureFragmentDirections
import pl.com.patryk.parcar.data.Departure
import pl.com.patryk.parcar.databinding.ListItemDepartureBinding

class DepartureAdapter: ListAdapter<Departure, DepartureAdapter.ViewHolder>(DepartureDiffCalback()){


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val departure = getItem(position)
        holder.apply {
            bind(createOnClickListener(departure),departure)
            itemView.tag = departure
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemDepartureBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    private fun createOnClickListener(departure: Departure):View.OnClickListener
    {
        return View.OnClickListener {
            val direction = DepartureFragmentDirections.actionDepartureFragmentToDepartureOptionsDialog(departure.id!!,departure.plate!!)
            it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(private val binding: ListItemDepartureBinding):RecyclerView.ViewHolder(binding.root)
    {

        fun bind(listener: View.OnClickListener,item:Departure)
        {
            binding.apply {
                imageButtonEdit.setOnClickListener(listener)
                departure = item
                executePendingBindings()


            }
        }
    }

    private class DepartureDiffCalback: DiffUtil.ItemCallback<Departure>()
    {
        override fun areContentsTheSame(oldItem: Departure, newItem: Departure): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Departure, newItem: Departure): Boolean {
            return oldItem.id == newItem.id
        }
    }
}