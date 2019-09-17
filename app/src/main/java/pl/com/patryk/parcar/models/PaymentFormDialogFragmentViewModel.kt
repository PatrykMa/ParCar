package pl.com.patryk.parcar.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.jetbrains.anko.doAsync
import pl.com.patryk.parcar.data.PaymentForm
import pl.com.patryk.parcar.data.PaymentFormRepository

class PaymentFormDialogFragmentViewModel internal constructor(paymentFormRepository: PaymentFormRepository) : ViewModel() {

    private val paymentFormRepository =paymentFormRepository
    fun delete(id:Long)
    {
        doAsync { paymentFormRepository.delete(paymentFormRepository.getPeymentForm(id.toInt())) }
    }
}