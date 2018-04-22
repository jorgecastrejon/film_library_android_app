package com.jcastrejon.filmlibrary.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private const val FORMAT = "yyyy-MM-dd"

fun String.justYear(): String {
    val parser = SimpleDateFormat(FORMAT, Locale.ENGLISH)

    return try {
        val date = parser.parse(this)
        val calendar = Calendar.getInstance().apply { time = date }
        calendar.get(Calendar.YEAR).toString()
    } catch (e: ParseException) {
        this
    }
}