package com.lunaflow.app.ui.theme

import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import com.lunaflow.app.R

object Fonts {
    @OptIn(ExperimentalTextApi::class)
    val interMedium = FontFamily(
        Font(
            R.font.inter,
            variationSettings = FontVariation.Settings(
                FontVariation.weight(FontWeight.Medium.weight)
            )
        )
    )

    @OptIn(ExperimentalTextApi::class)
    val interRegular = FontFamily(
        Font(
            R.font.inter,
            variationSettings = FontVariation.Settings(
                FontVariation.weight(FontWeight.Normal.weight)
            )
        )
    )

    @OptIn(ExperimentalTextApi::class)
    val interBold = FontFamily(
        Font(
            R.font.inter,
            variationSettings = FontVariation.Settings(
                FontVariation.weight(FontWeight.Bold.weight)
            )
        )
    )

    @OptIn(ExperimentalTextApi::class)
    val interSemiBold = FontFamily(
        Font(
            R.font.inter,
            variationSettings = FontVariation.Settings(
                FontVariation.weight(FontWeight.SemiBold.weight)
            )
        )
    )
}