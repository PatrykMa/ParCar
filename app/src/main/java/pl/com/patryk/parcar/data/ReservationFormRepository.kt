package pl.com.patryk.parcar.data

class ReservationFormRepository private constructor(private val reservationFormDao: ReservationFormDao){

    fun getReservatonsForms() = reservationFormDao.getReservationsForms()

    fun getreservatonForm(id:Long) = reservationFormDao.getReservationForm(id)

    fun delete(reservationForm: ReservationForm) = reservationFormDao.delete(reservationForm)

    fun insert(reservationForm: ReservationForm) = reservationFormDao.insert(reservationForm)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: ReservationFormRepository? = null

        fun getInstance(reservationFormDao: ReservationFormDao) =
            instance ?: synchronized(this) {
                instance ?: ReservationFormRepository(reservationFormDao).also { instance = it }
            }
    }

}