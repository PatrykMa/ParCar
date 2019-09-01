package pl.com.patryk.parcar.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import pl.com.patryk.parcar.workers.InitDadabaseWorker


const val DATABASE_NAME = "mrcar-db8"
@Database(entities = [Departure::class,PaymentForm::class,Reservation::class],version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun departureDao():DepartureDao
    abstract fun paymentFormDao():PaymentFormDao
    abstract fun rezervationDao():ReservationDao

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
                        val request = OneTimeWorkRequestBuilder<InitDadabaseWorker>().build()
                        WorkManager.getInstance(context).enqueue(request)
                    }
                })
                .build()
        }
    }

}