package pl.com.patryk.parcar.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.com.patryk.parcar.DepartureFragmentDirections
import pl.com.patryk.parcar.data.Reservation
import pl.com.patryk.parcar.databinding.ListItemReservationBinding
import pl.com.patryk.parcar.views.ReservationFragmentDirections

class ReservationAdapter : ListAdapter<Reservation, ReservationAdapter.ViewHolder>(ReservationDiffCallback()){
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reservation = getItem(position)
        holder.apply {
            bind(createOnClickListener(reservation),reservation)
            itemView.tag = reservation
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemReservationBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    private fun createOnClickListener(reservation: Reservation): View.OnClickListener
    {
        return View.OnClickListener {
            val direction = ReservationFragmentDirections.actionReservationFragmentToReservationDialogFragment(reservation.id!!,reservation.fromDateToString())
            it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(private val binding: ListItemReservationBinding): RecyclerView.ViewHolder(binding.root)
    {

        fun bind(listener: View.OnClickListener, item: Reservation)
        {
            binding.apply {
                imageButton.setOnClickListener(listener)
                reservation = item
                executePendingBindings()


            }
        }
    }

    private class ReservationDiffCallback: DiffUtil.ItemCallback<Reservation>()
    {
        override fun areContentsTheSame(oldItem: Reservation, newItem: Reservation): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Reservation, newItem: Reservation): Boolean {
            return oldItem.id == newItem.id
        }
    }
}