package pl.com.patryk.parcar

import java.text.SimpleDateFormat

class TimeTranslator {
    companion object{

        @JvmStatic
        fun toDateString(time:Long):String
        {
            val dateFormat = SimpleDateFormat("yy:MM:dd")
            return dateFormat.format(time)
        }

        @JvmStatic
        fun toTimeString(time:Long):String
        {
            val timeFormat = SimpleDateFormat("HH:mm")
            return timeFormat.format(time)
        }

    }
}