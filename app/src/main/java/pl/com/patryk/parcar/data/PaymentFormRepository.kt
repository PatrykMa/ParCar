package pl.com.patryk.parcar.data

class PaymentFormRepository private constructor(private val paymentFormDao: PaymentFormDao){

    fun getPaymentsForm() = paymentFormDao.getPaymentsForms()

    fun getPsymentForm(id:Int) = paymentFormDao.getPaymentForm(id)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: PaymentFormRepository? = null

        fun getInstance(paymentFormDao: PaymentFormDao) =
            instance ?: synchronized(this) {
                instance ?: PaymentFormRepository(paymentFormDao).also { instance = it }
            }
    }

}