package pl.com.patryk.parcar.models

import android.view.animation.Transformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel;
import org.jetbrains.anko.doAsync
import pl.com.patryk.parcar.data.*
import java.text.SimpleDateFormat
import java.util.*

class AddReservationViewModel internal constructor(
    val reservationRepository: ReservationRepository,
    val reservationFormRepository: ReservationFormRepository
):ViewModel() {

    private val _reservation:MutableLiveData<Reservation> = MutableLiveData<Reservation>().apply {
        value= Reservation().apply {
        arrivalDate = Calendar.getInstance().time.time
    }}
    val reservation:LiveData<Reservation> = _reservation
    val reservationForm = Transformations.map(reservationFormRepository.getReservatonsForms(),::reservationFormToString)
    private fun reservationFormToString(data:List<ReservationForm>) = Array<String>(data.size,{i->data[i].name})
    var date:String
        get() {val timeFormat = SimpleDateFormat("yy:MM:dd")
            return timeFormat.format(reservation.value!!.arrivalDate)}
        private set(value) {}
    var time :String
        get() {val timeFormat = SimpleDateFormat("HH:mm")
            return timeFormat.format(reservation.value!!.arrivalDate)}
        private set(value) {}

    fun setReservation(id:Long)
    {
        doAsync { _reservation.postValue(reservationRepository.getreservaton(id)) }
    }

    fun save(){
        doAsync { reservationRepository.insert(reservation.value!!) }
    }
}
