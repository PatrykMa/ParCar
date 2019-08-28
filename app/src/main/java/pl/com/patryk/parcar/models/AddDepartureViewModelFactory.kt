package pl.com.patryk.parcar.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pl.com.patryk.parcar.data.AppDatabase

class AddDepartureViewModelFactory (
    private val repository: AppDatabase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddDepartureViewModel(repository) as T
    }
}