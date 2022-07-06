package com.nonnewtonian.firstfa.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) = MaterialTheme(
    colors = lightColors(
        primary = Color(0xff533ea0),
        primaryVariant = Color(0xFFA6262E),
        secondary = Color(0xFF03C4DD),
        secondaryVariant = Color(0xFF03B2C9),
        surface = Color(0xFF837aa5)
    )
) {
    content()
}