package com.nonnewtonian.firstfa.ui

import android.util.Log
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
import androidx.navigation.NavController
import com.nonnewtonian.firstfa.R
import com.nonnewtonian.firstfa.navigation.Screens

@Composable
fun NavScreen(
    navController: NavController,
    modifier: Modifier = Modifier
        .fillMaxWidth()
){
    Surface(
        modifier.background(color = Color.Gray)
    ) {
        Log.d("navScreen", "nav screen made")
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

            MultiplicationButton({ navController.navigate(Screens.TrainingScreen.name) })
            DivisionButton()
            AdditionButton()
            SubtractionButton()
        }
    }
}
