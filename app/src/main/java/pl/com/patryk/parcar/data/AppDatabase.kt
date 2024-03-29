package pl.com.patryk.parcar.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import pl.com.patryk.parcar.workers.InitDatabaseWorker


const val DATABASE_NAME = "mrcar-db11"
@Database(entities = [Departure::class,PaymentForm::class,Reservation::class,ReservationForm::class],version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun departureDao():DepartureDao
    abstract fun paymentFormDao():PaymentFormDao
    abstract fun reservationDao():ReservationDao
    abstract fun reservationFormDao():ReservationFormDao

    companion object{
        //for singleton
        @Volatile private var instance:AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request = OneTimeWorkRequestBuilder<InitDatabaseWorker>().build()
                        WorkManager.getInstance(context).enqueue(request)
                    }
                })
                .build()
        }
    }

}