package pl.com.patryk.parcar.models

import androidx.lifecycle.*
import org.jetbrains.anko.doAsync
import pl.com.patryk.parcar.data.AppDatabase
import pl.com.patryk.parcar.data.Departure
import pl.com.patryk.parcar.data.PaymentForm
import java.util.*

class AddDepartureViewModel internal constructor(
    val database: AppDatabase):ViewModel() {

    companion object {

    var _departure= Departure().apply {
        initDeparture()
    }
        fun initDeparture()
        {
            _departure = Departure().apply {
                price = 50_00
                from = Calendar.getInstance().time.time
                to = Calendar.getInstance().time.time + 604800_000 //add oe week
            }
        }
    }
    private val mutableDeparture = MutableLiveData<Departure>().apply { value= _departure }
    val departure:LiveData<Departure> = mutableDeparture
    var price
        get() = _departure.price
        set(value) {
            _departure.price = value}

    var plate :String
        get() {return _departure.plate?:""}
        set(value) { _departure.plate = value}

    var additionalInformation:String
        get() { return _departure.additionalInformatioin ?: ""}
        set(value) { _departure.additionalInformatioin = value}
    private var _from = MutableLiveData<Long>(_departure.from)
    var from:Long
        get() {return _departure.from}
        set(value) {_from.postValue(value)
        _departure.from = value}
    private var _to = MutableLiveData<Long>(_departure.to)
    var to:Long
        get() {return _departure.to}
        set(value) {
            _to.postValue(value)
            _departure.to = value}
    var toTime
        get() = _departure.getToTime()
        private set(value) {}
    var toDate
        get() =  _departure.getToDate()
        private set(value) {}
    var fromTime
        get() = _departure.getFromTime()
        private set(value) {}
    var fromDate
        get() =  _departure.getFromDate()
        private set(value) {}
    var paymentForm:String
        get() {return _departure.paymentForm?: ""}
        set(value) {_departure.paymentForm = value }


    val paymentForms:LiveData<Array<String>> = Transformations.map(database.paymentFormDao().getPaymentsForms(), ::t);

    private fun t(data:List<PaymentForm>) = Array<String>(data.size,{i-> data[i].name})


    fun addDeparture()
    {

        doAsync {
            _departure.let {
                database.departureDao().insertAll(listOf(it))
            }
        }

    }

    fun setDeparture(id:Int)
    {
        if (id == 0)
            initDeparture()
        else
        doAsync {

            _departure = database.departureDao().getDeparture(id)

            mutableDeparture.postValue(_departure)
        }
    }





}