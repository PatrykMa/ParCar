package pl.com.patryk.parcar.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface DepartureDao {
    @Query("SELECT * FROM departure ORDER BY 'to'")
    fun getDepartures(): LiveData<List<Departure>>

    @Query("SELECT * FROM departure WHERE isPresent ORDER BY 'to'")
    fun getPresentDepartures(): LiveData<List<Departure>>

    @Query("SELECT * FROM departure WHERE id = :departureId")
    fun getDeparture(departureId: Int): LiveData<Departure>

    //TODO poprawić zapytanie o isPresent
    @Query("SELECT * FROM departure WHERE plate like :filter OR additionalInformatioin like :filter" )
    fun getDepartureWithFilter(filter: String): LiveData<List<Departure>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(departures: List<Departure>)
}