package com.accellion.moviecompanion.util

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*


class Utility {

    companion object {
        @JvmStatic
        fun loadDataFromAssets(context: Context, inFile: String?): String? {
            var tContents: String? = ""
            try {
                val stream: InputStream = context.assets.open(inFile!!)
                val size: Int = stream.available()
                val buffer = ByteArray(size)
                stream.read(buffer)
                stream.close()
                tContents = String(buffer)
            } catch (e: IOException) {
                return "";
            }
            return tContents
        }

        @JvmStatic
        fun convertDateFormat(context: Context, serverDate: String?): String? {
            var date = Constants.DEFAULT_VALUE
            if (TextUtils.isEmpty(serverDate))
                return date;
            try {
                var spf = SimpleDateFormat("yyyy-mm-dd")
                val newDate: Date = spf.parse(serverDate)
                spf = SimpleDateFormat("MMM dd, YYYY")
                date = spf.format(newDate)
                return date

            } catch (e: IOException) {
                return date;
            }
            return date
        }

        @JvmStatic
        fun saveToSharedPref(context: Context, serverDate: String?) {
            val sharedPref: SharedPreferences = context.getSharedPreferences("movie_list", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()

            val setKey: Set<String> = sharedPref.getStringSet("key", null) as Set<String>
            val set: MutableSet<String> = HashSet()
            set.add(serverDate!!)
            editor.putStringSet("key", setKey)
            editor.commit()
        }
    }
}