package pl.com.patryk.parcar.data

class PaymentFormRepository private constructor(private val paymentFormDao: PaymentFormDao){

    fun getPaymentsForm() = paymentFormDao.getPaymentsForms()

    fun getPeymentForm(id:Int) = paymentFormDao.getPaymentForm(id)

    fun delete(paymentForm: PaymentForm) = paymentFormDao.delete(paymentForm)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: PaymentFormRepository? = null

        fun getInstance(paymentFormDao: PaymentFormDao) =
            instance ?: synchronized(this) {
                instance ?: PaymentFormRepository(paymentFormDao).also { instance = it }
            }
    }

}