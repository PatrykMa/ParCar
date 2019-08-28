package pl.com.patryk.parcar.views

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import pl.com.patryk.parcar.R

class DepartureOptionsDialog: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.HORIZONTAL
        val editButton = Button(context)
        editButton.setOnClickListener{

        }

        val arriveButton = Button(context)
        arriveButton.setOnClickListener {  }

        layout.addView(editButton)
        layout.addView(arriveButton)

        val alert = AlertDialog.Builder(context!!)
            .setIcon(R.drawable.ic_menu_share)
            .setTitle("Opcje")
            .setView(layout)
            .setCancelable(true)

        return alert.create()
    }
}