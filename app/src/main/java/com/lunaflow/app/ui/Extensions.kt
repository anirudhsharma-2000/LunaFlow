package com.lunaflow.app.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun Modifier.horizontalFadeMask(): Modifier = this
    .graphicsLayer { alpha = 0.99f }
    .drawWithContent {
        drawContent()
        drawRect(
            brush = Brush.horizontalGradient(
                0f to Color.Transparent,
                0.25f to Color.Black,
                0.75f to Color.Black,
                1f to Color.Transparent
            ),
            blendMode = BlendMode.DstIn
        )
    }

@OptIn(ExperimentalMaterial3Api::class)
fun dismissDialog(
    onDismiss: () -> Unit,
    coroutineScope: CoroutineScope,
    sheetState: SheetState
) {
    onDismiss()
    coroutineScope.launch { sheetState.hide() }
}