package pl.com.patryk.parcar.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pl.com.patryk.parcar.data.DepartureRepository

class DepartureViewModelFactory(
    private val repository: DepartureRepository)
    :ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = DepartureViewModel(repository) as T
}