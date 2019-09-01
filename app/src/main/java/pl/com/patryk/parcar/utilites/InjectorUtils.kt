package pl.com.patryk.parcar.utilites

import android.content.Context
import pl.com.patryk.parcar.data.AppDatabase
import pl.com.patryk.parcar.data.DepartureRepository
import pl.com.patryk.parcar.models.*

object InjectorUtils
{

    private fun getDepartureRepository(context: Context): DepartureRepository {
        return DepartureRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).departureDao())
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
        return ReservationViewModelFactory(AppDatabase.getInstance(context.applicationContext).rezervationDao())
    }


}