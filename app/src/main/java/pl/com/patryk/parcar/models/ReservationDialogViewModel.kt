package pl.com.patryk.parcar.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.jetbrains.anko.doAsync
import pl.com.patryk.parcar.data.Reservation
import pl.com.patryk.parcar.data.ReservationRepository

class ReservationDialogViewModel (val reservationRepository: ReservationRepository) : ViewModel() {



    fun delete(id:Long)
    {
        doAsync { reservationRepository.delete(reservationRepository.getreservaton(id))}
    }

    fun getReservation(id:Long):Reservation
    {
        return reservationRepository.getreservaton(id)
    }
}