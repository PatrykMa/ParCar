package pl.com.patryk.parcar.models

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import pl.com.patryk.parcar.data.Departure
import pl.com.patryk.parcar.data.DepartureRepository
import androidx.lifecycle.switchMap

class DepartureViewModel internal constructor(departureRepository:DepartureRepository) : ViewModel() {

    private val filter = MutableLiveData<String>().apply { value = "" }

    val departures:LiveData<List<Departure>> = departureRepository.getPresentDeparture()

    fun setSearch(filter:String)
    {
        this.filter.value = filter
    }

    fun clearSearch()
    {
        filter.value = ""
    }
}
