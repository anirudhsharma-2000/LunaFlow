package com.lunaflow.app.ui.calendar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lunaflow.app.ui.calendar.widget.YearMonthBottomSheet
import com.lunaflow.app.ui.navigation.Screens
import com.lunaflow.app.ui.theme.Fonts

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
        TopBar(modifier, monthYear) { viewModel.onSheetState(true) }
        Placeholder(modifier = Modifier.height(250.dp), text = "CalendarView")
        Placeholder(modifier = Modifier.height(100.dp), text = "Insight")
        Placeholder(modifier = Modifier.height(100.dp), text = "Cycle Dates")
        Placeholder(modifier = Modifier.height(80.dp), text = "Buttons")
    }
    AnimatedVisibility(sheetState) {
        YearMonthBottomSheet(
            selectedYear,
            selectedMonth = selectedMonth,
            years,
            months,
            onYearSelect = viewModel::onYearChanged,
            onMonthChange = viewModel::onMonthSelected
        ) {
            viewModel.onSheetState(false)
        }
    }
}

@Composable
fun Placeholder(modifier: Modifier = Modifier, text: String) {
    Box(
        modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .border(1.dp, Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(text)
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier, monthYear: String, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            Modifier.clickable(onClick = onClick),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                monthYear,
                fontFamily = Fonts.interBold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.secondary
            )
            Icon(
                modifier = Modifier.size(14.dp),
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null
            )
        }
        Text(
            "Today", modifier = Modifier
                .padding(10.dp)
                .border(
                    1.dp,
                    color = MaterialTheme.colorScheme.scrim,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(horizontal = 10.dp, vertical = 4.dp),
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary,
            fontFamily = Fonts.interRegular
        )
    }
}

