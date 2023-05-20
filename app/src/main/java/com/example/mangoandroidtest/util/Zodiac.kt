package com.example.mangoandroidtest.util

class Zodiac {
//    val day = 19
//    val month = "may"

    fun zodiacSign(day: Int, month: String) {
        var astroSign = ""

        // checks month and date within the
        // valid range of a specified zodiac
        if (month === "december") {
            astroSign = if (day < 22) "Sagittarius" else "capricorn"
        } else if (month === "january") {
            astroSign = if (day < 20) "Capricorn" else "aquarius"
        } else if (month === "february") {
            astroSign = if (day < 19) "Aquarius" else "pisces"
        } else if (month === "march") {
            astroSign = if (day < 21) "Pisces" else "aries"
        } else if (month === "april") {
            astroSign = if (day < 20) "Aries" else "taurus"
        } else if (month === "may") {
            astroSign = if (day < 21) "Taurus" else "gemini"
        } else if (month === "june") {
            astroSign = if (day < 21) "Gemini" else "cancer"
        } else if (month === "july") {
            astroSign = if (day < 23) "Cancer" else "leo"
        } else if (month === "august") {
            astroSign = if (day < 23) "Leo" else "virgo"
        } else if (month === "september") {
            astroSign = if (day < 23) "Virgo" else "libra"
        } else if (month === "october") {
            astroSign = if (day < 23) "Libra" else "scorpio"
        } else if (month === "november") {
            astroSign = if (day < 22) "scorpio" else "sagittarius"
        }
        println(astroSign)
    }
}