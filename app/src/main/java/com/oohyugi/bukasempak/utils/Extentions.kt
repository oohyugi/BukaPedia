package com.oohyugi.bukasempak.utils

import android.content.Context
import android.graphics.Paint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import android.util.TypedValue
import android.widget.TextView
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.icu.text.NumberFormat
import java.text.DecimalFormat


/**
 * Created by oohyugi on 2019-04-24.
 * github: https://github.com/oohyugi
 */

@Throws(IOException::class)
fun Context.readJSONFromAsset(fileName:String): String? {
    val json: String?
    try {
        val  inputStream: InputStream = this.assets.open(fileName)
        json = inputStream.bufferedReader().use{it.readText()}
    } catch (ex: Exception) {
        ex.printStackTrace()
        return null
    }
    return json
}

fun FragmentActivity.replaceFragment(fragment: Fragment?, idContainer: Int, tag: String?) {
    this.supportFragmentManager?.beginTransaction()
        ?.replace(idContainer, fragment!!, tag)
        ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ?.commit()
}
fun Context.convertDip2Pixels(dip: Int): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip.toFloat(), this.resources.displayMetrics)
        .toInt()
}


fun TextView.setStrikeStrought(){
    this.paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

}

fun Double.indonesiaFormat(): String {

    return String.format("Rp.%,.0f", this).replace(",".toRegex(), ".")
}

fun Int.formatCurrency(): String {
    var value = this
    val arr = arrayOf("", "Rb", "Jt", "B", "T", "P", "E")
    var index = 0
    while (value / 1000 >= 1) {
        value = value / 1000
        index++
    }
    val decimalFormat = DecimalFormat("#.##")
    return String.format("Rp%s%s", decimalFormat.format(value), arr[index])
}