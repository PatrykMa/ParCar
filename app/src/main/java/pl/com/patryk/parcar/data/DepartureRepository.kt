package pl.com.patryk.parcar.data

class DepartureRepository private constructor(private val departureDao: DepartureDao) {

    fun getDepartures() = departureDao.getDepartures()

    fun getDeparture(departureId:Int) =departureDao.getDeparture(departureId)

    fun getPresentDeparture()= departureDao.getPresentDepartures()

    fun searchDeparture(filter:String) = departureDao.getDepartureWithFilter("%$filter%")

    companion object{

        @Volatile private var instance: DepartureRepository? = null

        fun getInstance(departueDao: DepartureDao) =
                instance ?: synchronized(this){
                    instance ?: DepartureRepository(departueDao).also { instance = it }
                }
    }
}