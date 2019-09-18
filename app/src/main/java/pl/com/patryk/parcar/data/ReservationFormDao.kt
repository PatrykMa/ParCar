package pl.com.patryk.parcar.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ReservationFormDao {
    @Query("SELECT * FROM _reservation_form ORDER BY name")
    fun getReservationsForms(): LiveData<List<ReservationForm>>

    @Query("SELECT * FROM _reservation_form WHERE id = :paymentId")
    fun getReservationForm(paymentId: Long): ReservationForm

    @Query("SELECT * FROM _reservation_form WHERE name = :name")
    fun getReservationFormByName(name: String): ReservationForm

    @Delete
    fun delete(reservationForm:ReservationForm)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(payments: ReservationForm)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(payments: List<ReservationForm>)
}