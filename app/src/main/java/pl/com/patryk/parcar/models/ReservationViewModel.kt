package pl.com.patryk.parcar.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pl.com.patryk.parcar.data.Reservation
import pl.com.patryk.parcar.data.ReservationDao

class ReservationViewModel(val departureRepository: ReservationDao) : ViewModel() {

    val reservations :LiveData<List<Reservation>> = departureRepository.getReservations()
}