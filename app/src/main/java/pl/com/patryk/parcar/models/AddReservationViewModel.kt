package pl.com.patryk.parcar.models

import androidx.lifecycle.ViewModel;
import pl.com.patryk.parcar.data.AppDatabase
import pl.com.patryk.parcar.data.Reservation
import pl.com.patryk.parcar.data.ReservationDao
import java.text.SimpleDateFormat

class AddReservationViewModel internal constructor(
    val database: ReservationDao
):ViewModel() {

    var reservation:Reservation = Reservation()
    var date:String
        get() {val timeFormat = SimpleDateFormat("yy:MM:dd")
            return timeFormat.format(reservation.arrivalDate)}
        private set(value) {}
    var time :String
        get() {val timeFormat = SimpleDateFormat("HH:mm")
            return timeFormat.format(reservation.arrivalDate)}
        private set(value) {}
}
