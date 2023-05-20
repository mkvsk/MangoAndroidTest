package com.example.mangoandroidtest.util

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object FormatUtils {

    val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    var dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")

//TODO zodiac
}