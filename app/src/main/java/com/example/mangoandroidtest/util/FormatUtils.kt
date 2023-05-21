package com.example.mangoandroidtest.util

import java.time.Month
import java.time.format.DateTimeFormatter

object FormatUtils {

    val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    var dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")

    fun identifyZodiacSign(day: Int, month: Month): String {
        var zodiacSign = ""
        if (month == Month.DECEMBER) {
            zodiacSign = if (day < 22) "Sagittarius" else "capricorn"
        } else if (month == Month.JANUARY) {
            zodiacSign = if (day < 20) "Capricorn" else "aquarius"
        } else if (month == Month.FEBRUARY) {
            zodiacSign = if (day < 19) "Aquarius" else "pisces"
        } else if (month == Month.MARCH) {
            zodiacSign = if (day < 21) "Pisces" else "aries"
        } else if (month == Month.APRIL) {
            zodiacSign = if (day < 20) "Aries" else "taurus"
        } else if (month == Month.MAY) {
            zodiacSign = if (day < 21) "Taurus" else "gemini"
        } else if (month == Month.JUNE) {
            zodiacSign = if (day < 21) "Gemini" else "cancer"
        } else if (month == Month.JULY) {
            zodiacSign = if (day < 23) "Cancer" else "leo"
        } else if (month == Month.AUGUST) {
            zodiacSign = if (day < 23) "Leo" else "virgo"
        } else if (month == Month.SEPTEMBER) {
            zodiacSign = if (day < 23) "Virgo" else "libra"
        } else if (month == Month.OCTOBER) {
            zodiacSign = if (day < 23) "Libra" else "scorpio"
        } else if (month == Month.NOVEMBER) {
            zodiacSign = if (day < 22) "scorpio" else "sagittarius"
        }
        return zodiacSign
    }

}