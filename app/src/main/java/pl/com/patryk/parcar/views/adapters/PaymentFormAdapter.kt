package pl.com.patryk.parcar.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.com.patryk.parcar.PaymentMethodFragmentDirections
import pl.com.patryk.parcar.data.PaymentForm
import pl.com.patryk.parcar.databinding.ListItemDepartureBinding
import pl.com.patryk.parcar.databinding.ListItemPaymentFormBinding

class PaymentFormAdapter : ListAdapter<PaymentForm, PaymentFormAdapter.ViewHolder>(PaymentFormDiffCallback()){


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val departure = getItem(position)
        holder.apply {
            bind(createOnClickListener(departure),departure)
            itemView.tag = departure
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemPaymentFormBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    private fun createOnClickListener(paymentForm: PaymentForm):View.OnClickListener
    {
        return View.OnClickListener {
            val direction = PaymentMethodFragmentDirections.
                actionPaymentMethodFragmentToPaymentFormDialogfragment(paymentForm.name,paymentForm.id!!)
            it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(private val binding: ListItemPaymentFormBinding): RecyclerView.ViewHolder(binding.root)
    {

        fun bind(listener: View.OnClickListener, item:PaymentForm)
        {
            binding.apply {
                buttonEdit.setOnClickListener(listener)
                paymentForm = item
                executePendingBindings()


            }
        }
    }

    private class PaymentFormDiffCallback: DiffUtil.ItemCallback<PaymentForm>()
    {
        override fun areContentsTheSame(oldItem: PaymentForm, newItem: PaymentForm): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: PaymentForm, newItem: PaymentForm): Boolean {
            return oldItem.id == newItem.id
        }
    }
}