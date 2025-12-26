package com.lunaflow.app.ui.calendar.widget

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Slider
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lunaflow.app.ui.dismissDialog
import com.lunaflow.app.ui.theme.Fonts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogPeriodBottomSheet(isSheetOpen: Boolean, onDismiss: () -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    val startDestination = PeriodsLog.MOOD
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }
    AnimatedVisibility(isSheetOpen) {
        ModalBottomSheet(onDismissRequest = {
            dismissDialog(onDismiss, coroutineScope, sheetState)
        }) {
            Column(
                Modifier
                    .padding(bottom = 20.dp)
                    .padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text("Quick Log")
                Text("Today's Date")
                PrimaryTabRow(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.Black.copy(0.1f)),
                    divider = {},
                    selectedTabIndex = selectedDestination
                ) {
                    PeriodsLog.entries.forEachIndexed { index, type ->
                        Tab(selectedDestination == index, onClick = {
                            selectedDestination = index
                        }, text = {
                            Text(
                                type.name,
                                fontFamily = Fonts.interRegular,
                                fontSize = 8.sp,
                                maxLines = 1,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        })
                    }
                }
                Box(Modifier.height(150.dp)) {
                    when (selectedDestination) {
                        PeriodsLog.MOOD.ordinal -> {
                            Text("Mood")
                        }

                        PeriodsLog.SYMPTOMS.ordinal -> {
                            Text("Symptoms")
                        }

                        PeriodsLog.ENERGY.ordinal -> {
                            Text("Energy")
                        }

                        PeriodsLog.FLOW.ordinal -> {
                            Text("Flow")
                        }
                    }
                }
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                        .clip(RoundedCornerShape(10.dp))
                        .align(Alignment.CenterHorizontally),
                    thickness = 1.dp,
                    color = Color.Gray.copy(0.2f)
                )
                var crampIntensity by rememberSaveable { mutableStateOf("Moderate") }
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "CRAMPS INTENSITY",
                        fontFamily = Fonts.interRegular,
                        fontSize = 12.sp,
                        maxLines = 1,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        crampIntensity,
                        fontFamily = Fonts.interRegular,
                        fontSize = 12.sp,
                        maxLines = 1,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                var sliderPosition by remember { mutableStateOf(0f) }
                Slider(
                    modifier = Modifier.fillMaxWidth(),
                    value = sliderPosition,
                    steps = 3,
                    valueRange = 0f..4f,
                    onValueChange = {
                        sliderPosition = it
                        crampIntensity = when (it.toInt()) {
                            0 -> "No Pain"
                            1 -> "Very Mild"
                            2 -> "Mild"
                            3 -> "Moderate"
                            4 -> "Severe"
                            else -> "No Pain"
                        }
                    }
                )
                Text(
                    "NOTES",
                    fontFamily = Fonts.interRegular,
                    fontSize = 12.sp,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.secondary
                )
                TextField(value = "Add Quick Note", onValueChange = {})
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { dismissDialog(onDismiss, coroutineScope, sheetState) },
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
}

enum class PeriodsLog {
    MOOD,
    SYMPTOMS,
    ENERGY,
    FLOW
}