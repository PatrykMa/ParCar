package pl.com.patryk.parcar.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.jetbrains.anko.doAsync
import pl.com.patryk.parcar.data.ReservationForm
import pl.com.patryk.parcar.data.ReservationFormRepositor

class ReservationFormViewModel internal constructor(val reservationFormRepository: ReservationFormRepositor) : ViewModel() {

    val reservationForm: LiveData<List<ReservationForm>> = reservationFormRepository.getReservatonsForms()

    fun addReservationForm(name:String)
    {doAsync { reservationFormRepository.insert(ReservationForm(null,name)) }}

    fun delete(id:Long)
    {doAsync { reservationFormRepository.delete(reservationFormRepository.getreservatonForm(id)) }}
}