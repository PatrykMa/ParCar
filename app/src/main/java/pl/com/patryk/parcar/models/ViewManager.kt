package pl.com.patryk.parcar.models

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import pl.com.patryk.parcar.DepartureFragment
import pl.com.patryk.parcar.views.NamedFragment

class ViewManager:BroadcastReceiver() {

    companion object{

    }

    var aplication:AppCompatActivity? = null
    @IdRes var fragmentView: Int? = null
    enum class Views(val value:Int){
        Main(0),
        Departure(1);
        companion object{
            fun getByValue(value:Int):Views
            {
                values().forEach {
                    if(value == it.value)
                        return  it
                }
                throw Exception("unknown value")
            }
        }
    }

    override fun onReceive(p0: Context?, p1: Intent?) {
        var view = Views.getByValue(p1!!.getIntExtra("",1))
        if(aplication!= null && fragmentView != null)
        {
            val fragment:NamedFragment = when(view)
            {
                Views.Main -> DepartureFragment()
                Views.Departure -> DepartureFragment()
            }
            aplication!!.supportFragmentManager.beginTransaction().add(fragmentView!!,fragment)
        }
    }

    /*fun display(view:Views)
    {
        if(aplication!= null && fragmentView != null)
        {
            val fragment:NamedFragment = when(view)
            {
                Views.Main -> DepartureFragment()
                Views.Departure -> DepartureFragment()
            }
            aplication!!.supportFragmentManager.beginTransaction().add(fragmentView!!,fragment)
        }
    }*/
}