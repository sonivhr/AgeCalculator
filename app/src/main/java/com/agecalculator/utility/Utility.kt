package com.agecalculator.utility

import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

const val DATE_FORMAT = "dd-MMMM-yyyy"

private val daysInMonth = intArrayOf(
    31, 28, 31, 30, 31, 30,
    31, 31, 30, 31, 30, 31
)

private val daysInMonthInALeapYear = intArrayOf(
    31, 29, 31, 30, 31, 30,
    31, 31, 30, 31, 30, 31
)

fun calculateAgeInYearsMonthsDays(dateOfBirth: String): String {
    val birthDate: Calendar = Calendar.getInstance()
    birthDate.time =
        SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse(dateOfBirth) ?: Date()
    val birthDateDay = birthDate.get(Calendar.DAY_OF_MONTH)
    val birthDateMonth = birthDate.get(Calendar.MONTH) + 1
    val birthDateYear = birthDate.get(Calendar.YEAR)

    val calendar: Calendar = Calendar.getInstance()
    var currentDate = calendar.get(Calendar.DAY_OF_MONTH)
    var currentMonth = calendar.get(Calendar.MONTH) + 1
    var currentYear = calendar.get(Calendar.YEAR)

    /**
     * Is date/day of birth greater than today's date?
     *
     * If yes, we cannot add current month while calculating age
     *
     * Also if the birth date is greater than current date then we need to add days of month
     * of birth to the current date. This will increase current date but in the end we will
     * subtract birth date from current date to get correct number of days count.
     *
     * If year of birth is a Leap Year then we need to calculate 29 days in February month
     */
    if (birthDateDay > currentDate) {
        currentMonth -= 1
        currentDate += if (birthDate.get(Calendar.YEAR) % 4 == 0) {
            daysInMonthInALeapYear[birthDate.get(Calendar.MONTH)]
        } else {
            daysInMonth[birthDate.get(Calendar.MONTH)]
        }
    }

    /**
     * If month of birth is greater than current month then complete cycle of a year has not
     * completed. In this case we need to subtract 1 from year count.
     *
     * As a second step, we now need to add 12 months to current month, it will increase month
     * count above 12 but as we will subtract month of birth, we will get correct results of
     * month.
     */
    if (birthDateMonth > currentMonth) {
        currentYear -= 1
        currentMonth += 12
    }

    val age = StringBuilder()
    if (currentYear - birthDateYear > 0) {
        age.append("${currentYear - birthDateYear} Years ")
    }
    if (currentMonth - birthDateMonth > 0) {
        age.append("${currentMonth - birthDateMonth} Months ")
    }
    if (currentDate - birthDateDay > 0) {
        age.append("${currentDate - birthDateDay} Days")
    }

    return age.toString()

//    return "${currentYear - birthDateYear} Years " +
//            "${currentMonth - birthDateMonth} Months " +
//            "${currentDate - birthDateDay} Days"
}