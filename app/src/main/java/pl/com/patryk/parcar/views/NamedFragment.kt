package pl.com.patryk.parcar.views

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class NamedFragment:Fragment {

    constructor():super()
    constructor(@LayoutRes layout:Int):super(layout)
    var name = ""

}