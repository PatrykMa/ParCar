package pl.com.patryk.parcar.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ReservatonFormDao {
    @Query("SELECT * FROM paymentForm ORDER BY name")
    fun getReservationsForms(): LiveData<List<ReservationForm>>

    @Query("SELECT * FROM paymentForm WHERE id = :paymentId")
    fun getReservationForm(paymentId: Int): LiveData<ReservationForm>

    @Query("SELECT * FROM paymentForm WHERE name = :name")
    fun getReservationFormByName(name: String): ReservationForm

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(payments: List<PaymentForm>)
}