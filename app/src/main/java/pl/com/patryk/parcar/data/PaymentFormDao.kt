package pl.com.patryk.parcar.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface PaymentFormDao {
    @Query("SELECT * FROM paymentForm ORDER BY name")
    fun getPaymentsForms(): LiveData<List<PaymentForm>>

    @Query("SELECT * FROM paymentForm WHERE id = :paymentId")
    fun getPaymentForm(paymentId: Int): LiveData<PaymentForm>

    @Query("SELECT * FROM paymentForm WHERE name = :name")
    fun getPaymentFormByName(name: String): PaymentForm

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(payments: List<PaymentForm>)
}