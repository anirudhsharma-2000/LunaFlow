package com.lunaflow.app.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lunaflow.app.ui.calendar.widget.Buttons
import com.lunaflow.app.ui.calendar.widget.LogPeriodBottomSheet
import com.lunaflow.app.ui.calendar.widget.TopBar
import com.lunaflow.app.ui.calendar.widget.YearMonthBottomSheet
import com.lunaflow.app.ui.navigation.Screens

@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
    backstack: SnapshotStateList<Screens>,
    viewModel: CalendarViewModel = viewModel()
) {
    val monthYear = viewModel.monthYear.collectAsStateWithLifecycle().value
    val sheetState = viewModel.sheetOpen.collectAsStateWithLifecycle().value
    val selectedYear = viewModel.selectedYear.collectAsStateWithLifecycle().value
    val selectedMonth = viewModel.selectedMonth.collectAsStateWithLifecycle().value
    val years = viewModel.years.collectAsStateWithLifecycle().value
    val months = viewModel.months.collectAsStateWithLifecycle().value
    Column(modifier = modifier) {
        TopBar(
            monthYear = monthYear,
            onDateClick = { viewModel.onSheetState(true) },
            onTodayClick = { viewModel.setCurrentYearMonth() })
        Placeholder(modifier = Modifier.height(250.dp), text = "CalendarView")
        Placeholder(modifier = Modifier.height(100.dp), text = "Insight")
        Placeholder(modifier = Modifier.height(100.dp), text = "Cycle Dates")
        Buttons()
    }
    YearMonthBottomSheet(
        isSheetOpen = sheetState,
        selectedYear = selectedYear,
        selectedMonth = selectedMonth,
        years = years,
        months = months,
        onYearSelect = viewModel::onYearChanged,
        onMonthChange = viewModel::onMonthSelected,
        onDismiss = { viewModel.onSheetState(false) }
    )
    LogPeriodBottomSheet(false, onDismiss = { viewModel.onSheetState(false) })
}

@Composable
fun Placeholder(modifier: Modifier = Modifier, text: String) {
    Box(
        modifier
            .fillMaxWidth()
            .background(Color.Gray.copy(0.1f))
            .border(1.dp, Color.Black.copy(0.1f)),
        contentAlignment = Alignment.Center
    ) {
        Text(text)
    }
}