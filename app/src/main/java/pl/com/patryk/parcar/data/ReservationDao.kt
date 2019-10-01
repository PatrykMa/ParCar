package pl.com.patryk.parcar.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ReservationDao {
    @Query("SELECT * FROM _reservation ORDER BY arrivalDate")
    fun getReservations(): LiveData<List<Reservation>>


    @Query("SELECT * FROM _reservation WHERE id = :id LIMIT 1")
    fun getReservation(id: Long): Reservation

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(reservation: Reservation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(reservation: List<Reservation>)

    @Update
    fun update(reservation: Reservation)

    @Delete
    fun delete(reservation: Reservation)
}