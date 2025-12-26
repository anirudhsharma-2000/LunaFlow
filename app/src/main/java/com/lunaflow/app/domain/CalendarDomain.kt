package com.lunaflow.app.domain

import java.time.Month
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

object CalendarDomain {
    private val yearMonth by lazy { YearMonth.now() }

    // TODO: remove hardcode instead take user
    val years by lazy { (2016..yearMonth.year).toList() }
    val months by lazy { Month.entries.toTypedArray().map { it.name } }
    fun formatMonthYear(year: Int, month: Int): String {
        val yearMonth = YearMonth.of(year, month)
        val dateFormatter = DateTimeFormatter.ofPattern("MMMM, yyyy", Locale.ENGLISH)
        return yearMonth.format(dateFormatter)
    }

    fun current(): Pair<Int, Int> {
        return yearMonth.year to yearMonth.monthValue
    }

}