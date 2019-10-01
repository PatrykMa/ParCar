package pl.com.patryk.parcar.data

class ReservationRepository private constructor(private val reservationDao: ReservationDao){

    fun getReservatons() = reservationDao.getReservations()

    fun getreservaton(id:Long) = reservationDao.getReservation(id)

    fun delete(reservation: Reservation) = reservationDao.delete(reservation)

    fun insert(reservation: Reservation) = reservationDao.insert(reservation)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: ReservationRepository? = null

        fun getInstance(reservationDao: ReservationDao) =
            instance ?: synchronized(this) {
                instance ?: ReservationRepository(reservationDao).also { instance = it }
            }
    }

}