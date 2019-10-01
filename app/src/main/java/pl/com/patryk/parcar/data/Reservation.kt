package pl.com.patryk.parcar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat

@Entity(tableName = "_reservation")
class Reservation (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    var id:Long = -1,
    var quantit:Int =1,
    var reservationForm:String = "",
    var arrivalDate:Long = -1,
    var departureDate:Long = -1,
    var isPaid:Boolean = false
)
{
    fun getReservationFormName():String = reservationForm


    fun fromDateToString():String
    {
        val dateFormat = SimpleDateFormat("yy:MM:dd")
        return if(arrivalDate > 0) dateFormat.format(arrivalDate) else ""
    }
}