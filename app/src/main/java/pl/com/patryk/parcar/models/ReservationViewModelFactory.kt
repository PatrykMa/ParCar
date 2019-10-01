package pl.com.patryk.parcar.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pl.com.patryk.parcar.data.DepartureRepository
import pl.com.patryk.parcar.data.ReservationDao
import pl.com.patryk.parcar.data.ReservationRepository

class ReservationViewModelFactory (
    private val repository: ReservationRepository
)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = ReservationViewModel(repository) as T
}