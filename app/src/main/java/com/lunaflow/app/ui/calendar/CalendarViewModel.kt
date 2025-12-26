package com.lunaflow.app.ui.calendar

import androidx.lifecycle.ViewModel
import com.lunaflow.app.domain.CalendarDomain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CalendarViewModel : ViewModel() {
    private val _selectedYear = MutableStateFlow(0)
    val selectedYear = _selectedYear.asStateFlow()
    private val _selectedMonth = MutableStateFlow(0)
    val selectedMonth = _selectedMonth.asStateFlow()

    private val _monthYear = MutableStateFlow("")
    val monthYear = _monthYear.asStateFlow()
    private var _sheetOpen = MutableStateFlow(false)
    val sheetOpen = _sheetOpen.asStateFlow()
    val years = MutableStateFlow(CalendarDomain.years).asStateFlow()
    val months = MutableStateFlow(CalendarDomain.months).asStateFlow()

    init {
        val (year, month) = CalendarDomain.current()
        _selectedYear.value = year
        _selectedMonth.value = month
        updateMonthYear()
    }

    fun onYearChanged(year: Int) {
        if (_selectedYear.value != year) {
            _selectedYear.value = year
            updateMonthYear()
        }
    }

    fun onMonthSelected(month: Int) {
        _selectedMonth.value = month
        updateMonthYear()
    }

    fun onSheetState(isOpen: Boolean) {
        _sheetOpen.value = isOpen
    }

    private fun updateMonthYear() {
        _monthYear.value = CalendarDomain.formatMonthYear(
            _selectedYear.value,
            _selectedMonth.value
        )
    }
}
