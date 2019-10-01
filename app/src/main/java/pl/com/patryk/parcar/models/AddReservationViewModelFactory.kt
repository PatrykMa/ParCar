package pl.com.patryk.parcar.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pl.com.patryk.parcar.data.AppDatabase
import pl.com.patryk.parcar.data.ReservationDao
import pl.com.patryk.parcar.data.ReservationFormRepository
import pl.com.patryk.parcar.data.ReservationRepository

class AddReservationViewModelFactory (
    private val repository: ReservationRepository,
    private val formRepository: ReservationFormRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddReservationViewModel(repository,formRepository) as T
    }
}