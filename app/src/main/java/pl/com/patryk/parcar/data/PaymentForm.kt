package pl.com.patryk.parcar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "paymentForm")
data class PaymentForm (
    var name:String = "",
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    var id:Int? = null
){
}