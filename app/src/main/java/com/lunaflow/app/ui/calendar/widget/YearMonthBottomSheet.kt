package com.lunaflow.app.ui.calendar.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lunaflow.app.ui.horizontalFadeMask
import com.lunaflow.app.ui.theme.Fonts
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YearMonthBottomSheet(
    currentYear: Int,
    selectedMonth: Int,
    years: List<Int>,
    months: List<String>,
    onYearSelect: (Int) -> Unit,
    onMonthChange: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    val itemWidth = 64.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val sidePadding = (screenWidth / 2) - (itemWidth / 2)
    val sheetState = rememberModalBottomSheetState()
    val listState = rememberLazyListState(years.indexOf(currentYear).coerceAtLeast(0))
    val flingBehavior =
        rememberSnapFlingBehavior(lazyListState = listState, snapPosition = SnapPosition.Center)
    val haptic = LocalHapticFeedback.current

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo }
            .map { layoutInfo ->
                val viewportCenter =
                    (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2
                layoutInfo.visibleItemsInfo
                    .minByOrNull { abs((it.offset + it.size / 2) - viewportCenter) }?.index
            }
            .distinctUntilChanged()
            .collect {
                it?.let {
                    years.getOrNull(it)?.let { year ->
                        onYearSelect(year)
                        haptic.performHapticFeedback(
                            HapticFeedbackType.TextHandleMove
                        )
                    }
                }
            }
    }

    ModalBottomSheet(onDismissRequest = onDismiss, sheetState = sheetState) {
        LazyRow(
            Modifier
                .fillMaxWidth()
                .horizontalFadeMask(),
            state = listState,
            flingBehavior = flingBehavior,
            contentPadding = PaddingValues(horizontal = sidePadding),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(years) {
                val isSelected = it == currentYear
                Text(
                    text = it.toString(),
                    fontFamily = Fonts.interBold,
                    fontSize = if (isSelected) 18.sp else 14.sp,
                    color = if (isSelected) Color.White else Color.Gray,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    textAlign = TextAlign.Center

                )
            }
        }
        Spacer(Modifier.size(20.dp))
        Column(
            Modifier.padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                itemsIndexed(months) { index, month ->
                    val monthNumber = index + 1
                    val isSelected = monthNumber == selectedMonth

                    Text(
                        text = month.take(3),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                1.dp,
                                if (isSelected) Color.White else Color.Gray,
                                RoundedCornerShape(6.dp)
                            )
                            .background(
                                if (isSelected) Color.White.copy(alpha = 0.15f)
                                else Color.Transparent,
                                RoundedCornerShape(6.dp)
                            )
                            .clickable { onMonthChange(monthNumber) }
                            .padding(vertical = 12.dp, horizontal = 8.dp),
                        color = if (isSelected) Color.White else Color.Gray,
                        fontFamily = Fonts.interRegular,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onDismiss,
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Done",
                    fontFamily = Fonts.interRegular,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}