package com.example.mangoandroidtest.util

import java.time.Month
import java.time.format.DateTimeFormatter

object FormatUtils {

    val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    var dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")

    fun identifyZodiacSign(day: Int, month: Month): String {
        var zodiacSign = ""
        when (month) {
            Month.DECEMBER -> {
                zodiacSign = if (day < 22) "Sagittarius" else "capricorn"
            }
            Month.JANUARY -> {
                zodiacSign = if (day < 20) "Capricorn" else "aquarius"
            }
            Month.FEBRUARY -> {
                zodiacSign = if (day < 19) "Aquarius" else "pisces"
            }
            Month.MARCH -> {
                zodiacSign = if (day < 21) "Pisces" else "aries"
            }
            Month.APRIL -> {
                zodiacSign = if (day < 20) "Aries" else "taurus"
            }
            Month.MAY -> {
                zodiacSign = if (day < 21) "Taurus" else "gemini"
            }
            Month.JUNE -> {
                zodiacSign = if (day < 21) "Gemini" else "cancer"
            }
            Month.JULY -> {
                zodiacSign = if (day < 23) "Cancer" else "leo"
            }
            Month.AUGUST -> {
                zodiacSign = if (day < 23) "Leo" else "virgo"
            }
            Month.SEPTEMBER -> {
                zodiacSign = if (day < 23) "Virgo" else "libra"
            }
            Month.OCTOBER -> {
                zodiacSign = if (day < 23) "Libra" else "scorpio"
            }
            Month.NOVEMBER -> {
                zodiacSign = if (day < 22) "scorpio" else "sagittarius"
            }
        }
        return zodiacSign
    }

}