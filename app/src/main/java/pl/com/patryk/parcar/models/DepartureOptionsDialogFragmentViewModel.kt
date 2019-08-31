package pl.com.patryk.parcar.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.jetbrains.anko.doAsync
import pl.com.patryk.parcar.data.AppDatabase
import pl.com.patryk.parcar.data.Departure
import pl.com.patryk.parcar.data.DepartureRepository

class DepartureOptionsDialogFragmentViewModel internal constructor(
    val database: DepartureRepository
): ViewModel() {

    fun deleteDeparture(id:Int)
    {


            doAsync {
                val departure  = database.getDeparture(id)
                departure?.let {
                    it.isPresent=false
                    database.update(it)
            }
        }
    }
}