package pl.com.patryk.parcar.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pl.com.patryk.parcar.data.DepartureRepository
import pl.com.patryk.parcar.data.PaymentFormRepository

class PaymentFormViewModelFactory (
    private val repository: PaymentFormRepository
)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = PaymentFormViewModel(repository) as T
}