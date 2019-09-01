package pl.com.patryk.parcar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "_reservation")
class Reservation (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    var id:Int = -1,
    var quantit:Int =1,
    var rezervationForm:String = "",
    var arrivalDate:Long = -1,
    var departureDate:Long = -1,
    var isPaid:Boolean = false
)
{}