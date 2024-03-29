package pl.com.patryk.parcar.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.jetbrains.anko.doAsync
import pl.com.patryk.parcar.data.Reservation
import pl.com.patryk.parcar.data.ReservationDao
import pl.com.patryk.parcar.data.ReservationRepository

class ReservationViewModel(val reservationRepository: ReservationRepository) : ViewModel() {

    val reservations :LiveData<List<Reservation>> = reservationRepository.getReservatons()

}