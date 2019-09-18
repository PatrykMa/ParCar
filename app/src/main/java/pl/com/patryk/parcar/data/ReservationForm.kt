package pl.com.patryk.parcar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "_reservation_form")
class ReservationForm(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    var id:Long? = null,
    var name:String = "")
{
}