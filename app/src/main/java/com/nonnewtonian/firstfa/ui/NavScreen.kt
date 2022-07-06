package com.nonnewtonian.firstfa.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nonnewtonian.firstfa.R

@Preview
@Composable
fun NavScreen(
    modifier: Modifier = Modifier
        .fillMaxWidth()
){
    Surface(
        modifier.background(color = Color.Gray)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxHeight()
//                .background(color = Color(0xFF837aa5))
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_brainstorming),
                contentDescription = "Brainiac",
                modifier = Modifier
                    .padding(bottom = 20.dp)
            )

            MultiplicationButton()
            DivisionButton()
            AdditionButton()
            SubtractionButton()
        }
    }
}
