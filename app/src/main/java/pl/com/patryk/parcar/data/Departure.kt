package pl.com.patryk.parcar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "departure")
class Departure(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    var id:Int? = null,
    var from:Long? = null,
    var to:Long? = null,
    var paymentForm:String? = null,
    var plate:String? = null,
    var additionalInformatioin:String? = null,
    var price:Int? = null,
    var isPaid:Boolean? = null,
    var isPresent:Boolean? = null
){
    fun fromReadable():String{
        return  ""
    }

    fun toReadable():String
    {
        return ""
    }

    fun getFromTime():String{
        val timeFormat = SimpleDateFormat("HH:mm")
        return timeFormat.format(from)
    }

    fun getFromDate():String
    {
        val dateFormat = SimpleDateFormat("yy:MM:dd")
        return dateFormat.format(from)
    }

    fun getToTime():String{
        val timeFormat = SimpleDateFormat("HH:mm")
        return timeFormat.format(to)
    }

    fun getToDate():String{
        val dateFormat = SimpleDateFormat("yy:MM:dd")
        return dateFormat.format(to)
    }

    override fun toString(): String {
        return plate ?: ""
    }

}