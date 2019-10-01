package pl.com.patryk.parcar.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.coroutineScope
import pl.com.patryk.parcar.data.AppDatabase
import pl.com.patryk.parcar.data.PaymentForm
import pl.com.patryk.parcar.data.ReservationForm

class InitDatabaseWorker (
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {


        private val TAG by lazy { InitDatabaseWorker::class.java.simpleName }

        override suspend fun doWork(): Result = coroutineScope {

            try {
                    val database = AppDatabase.getInstance(applicationContext)
                    database.paymentFormDao().insertAll(listOf(PaymentForm("Gotówka")))
                    database.reservationFormDao().insert(ReservationForm(null,"Prywatnie"))

                    Result.success()

            } catch (ex: Exception) {
                Log.e(TAG, "Error seeding database", ex)
                Result.failure()
            }
        }
    }
