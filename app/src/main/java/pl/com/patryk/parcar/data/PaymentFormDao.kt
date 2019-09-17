package pl.com.patryk.parcar.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface PaymentFormDao {
    @Query("SELECT * FROM paymentForm ORDER BY name")
    fun getPaymentsForms(): LiveData<List<PaymentForm>>

    @Query("SELECT * FROM paymentForm WHERE id = :paymentId")
    fun getPaymentForm(paymentId: Int): PaymentForm

    @Query("SELECT * FROM paymentForm WHERE name = :name")
    fun getPaymentFormByName(name: String): PaymentForm

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(payments: List<PaymentForm>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(paymentForm: PaymentForm)

    @Delete
    fun delete(toDelete:PaymentForm)
}