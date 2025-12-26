package com.lunaflow.app.ui.calendar.widget

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lunaflow.app.ui.theme.Fonts

@Composable
fun TopBar(
    modifier: Modifier = Modifier.Companion,
    monthYear: String,
    onDateClick: () -> Unit,
    onTodayClick: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            Modifier.clickable(onClick = onDateClick),
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
                .clip(RoundedCornerShape(20.dp))
                .clickable(onClick = onTodayClick)
                .padding(horizontal = 10.dp, vertical = 4.dp),
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary,
            fontFamily = Fonts.interRegular
        )
    }
}