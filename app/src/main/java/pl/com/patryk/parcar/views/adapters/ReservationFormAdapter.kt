package pl.com.patryk.parcar.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.com.patryk.parcar.data.ReservationForm
import pl.com.patryk.parcar.databinding.ListItemReservationFormBinding
import pl.com.patryk.parcar.views.ReservationFormFragmentDirections


class ReservationFormAdapter: ListAdapter<ReservationForm, ReservationFormAdapter.ViewHolder>(ReservationFormDiffCallback()){


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val departure = getItem(position)
        holder.apply {
            bind(createOnClickListener(departure),departure)
            itemView.tag = departure
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemReservationFormBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    private fun createOnClickListener(reservationForm: ReservationForm): View.OnClickListener
    {
        return View.OnClickListener {
            val direction = ReservationFormFragmentDirections.
                actionReservationFormFragmentToReservationFormEditDialogFragment(reservationForm.name+ "test",reservationForm.id!!)
            it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(private val binding: ListItemReservationFormBinding): RecyclerView.ViewHolder(binding.root)
    {

        fun bind(listener: View.OnClickListener, item: ReservationForm)
        {
            binding.apply {
                buttonEdit.setOnClickListener(listener)
                reservationForm = item
                executePendingBindings()


            }
        }
    }

    private class ReservationFormDiffCallback: DiffUtil.ItemCallback<ReservationForm>()
    {
        override fun areContentsTheSame(oldItem: ReservationForm, newItem: ReservationForm): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: ReservationForm, newItem: ReservationForm): Boolean {
            return oldItem.id == newItem.id
        }
    }
}