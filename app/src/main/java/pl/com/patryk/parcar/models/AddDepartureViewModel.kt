package pl.com.patryk.parcar.models

import androidx.lifecycle.*
import org.jetbrains.anko.doAsync
import pl.com.patryk.parcar.data.AppDatabase
import pl.com.patryk.parcar.data.Departure
import pl.com.patryk.parcar.data.PaymentForm
import java.text.SimpleDateFormat
import java.util.*

class AddDepartureViewModel internal constructor(
    val database: AppDatabase):ViewModel() {

    companion object {

    val departure= Departure().apply {
        price = 50_00
        from = Calendar.getInstance().time.time
        to = Calendar.getInstance().time.time + 604800_000 //add oe week
    }
    }
    var price
        get() = departure.price
        set(value) {
            departure.price = value}

    var plate :String
        get() {return departure.plate?:""}
        set(value) { departure.plate = value}

    var additionalInformation:String
        get() { return departure.additionalInformatioin ?: ""}
        set(value) { departure.additionalInformatioin = value}
    private var _from = MutableLiveData<Long>(departure.from)
    var from:Long
        get() {return departure.from?: 0}
        set(value) {_from.postValue(value)
        departure.from = value}
    private var _to = MutableLiveData<Long>(departure.to)
    var to:Long
        get() {return departure.to?: 0}
        set(value) {_to.postValue(value)
            departure.to = value}
    var toTime
        get() = departure.getToTime()
        private set(value) {}
    var toDate
        get() =  departure.getToDate()
        private set(value) {}
    var fromTime
        get() = departure.getFromTime()
        private set(value) {}
    var fromDate
        get() =  departure.getFromDate()
        private set(value) {}
    var paymentForm:String
        get() {return departure.paymentForm?: ""}
        set(value) {departure.paymentForm = value }


    val paymentForms:LiveData<Array<String>> = Transformations.map(database.paymentFormDao().getPaymentsForms(), ::t);

    private fun t(data:List<PaymentForm>) = Array<String>(data.size,{i-> data[i].name})


    fun addDeparture()
    {

        doAsync {
            departure.let {
                database.departureDao().insertAll(listOf(it))
            }
        }

    }





}