package pl.com.patryk.parcar.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pl.com.patryk.parcar.data.PaymentForm
import pl.com.patryk.parcar.data.PaymentFormRepository


class PaymentFormViewModel internal constructor(paymentFormRepository: PaymentFormRepository) : ViewModel() {

    val paymentForms: LiveData<List<PaymentForm>> = paymentFormRepository.getPaymentsForm()
}