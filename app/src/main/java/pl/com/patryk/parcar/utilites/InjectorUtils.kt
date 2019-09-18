package pl.com.patryk.parcar.utilites

import android.content.Context
import pl.com.patryk.parcar.data.*
import pl.com.patryk.parcar.models.*

object InjectorUtils
{

    private fun getDepartureRepository(context: Context): DepartureRepository {
        return DepartureRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).departureDao())
    }
    private fun getReservationrepository(context: Context):ReservationDao
    {
        return AppDatabase.getInstance(context.applicationContext).reservationDao()
    }

    private fun getPaymentFormRepository(context: Context):PaymentFormRepository
    {
        return  PaymentFormRepository.getInstance(AppDatabase.getInstance(context.applicationContext).paymentFormDao())
    }

    private fun getReservationFormRepository(context: Context):ReservationFormRepositor
    {
        return  ReservationFormRepositor.getInstance(AppDatabase.getInstance(context.applicationContext).reservationFormDao())
    }



    fun provideDepartureViewModelFactory(context: Context): DepartureViewModelFactory {
        val repository = getDepartureRepository(context)
        return DepartureViewModelFactory(repository)
    }

    fun provideAddDepartureViewModelFactory(context: Context): AddDepartureViewModelFactory
    {
        return AddDepartureViewModelFactory(AppDatabase.getInstance(context))
    }

    fun provideDepartureOptionsDialogFragmentFactory(context: Context): DepartureOptionsDialogFragmentViewModelFactory
    {
        return DepartureOptionsDialogFragmentViewModelFactory(getDepartureRepository(context))
    }

    fun provideReservationViewModelFacory(context: Context):ReservationViewModelFactory
    {
        return ReservationViewModelFactory(AppDatabase.getInstance(context.applicationContext).reservationDao())
    }

    fun provideAddReservationViewModelFactory(context: Context):AddReservationViewModelFactory
    {
        return AddReservationViewModelFactory(getReservationrepository(context))
    }

    fun providePaymentFormViewModel(context: Context):PaymentFormViewModelFactory
    {
        return PaymentFormViewModelFactory(getPaymentFormRepository(context))
    }

    fun providePaymentFormDialogFragmentViewModel(context: Context):PaymentFormDialogFragmentViewModelFactory
    {
        return PaymentFormDialogFragmentViewModelFactory(getPaymentFormRepository(context))
    }

    fun provideReservationFormViewModelFactory(context: Context):ReservationFormViewModelFactory
    {
        return ReservationFormViewModelFactory(getReservationFormRepository(context))
    }


}