package com.nonnewtonian.firstfa.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.nonnewtonian.firstfa.model.HighScore
import com.nonnewtonian.firstfa.model.MathEliteViewModel
import com.nonnewtonian.firstfa.model.QuizViewModel
import com.nonnewtonian.firstfa.navigation.Screens

@Composable
fun ScoreScreen(
    navController: NavController,
    modifier: Modifier =
        Modifier,
    highScore: HighScore
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Gray)
    ) {
        Column(
            modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = highScore.score.toString(),
                fontSize = 40.sp
            )
            Text(
                text = highScore.trainingType.value,
                fontSize = 20.sp
            )
            Button(
                onClick = {navController.navigate(Screens.TrainingScreen.name)},
            ) {
                Text(text = "Replay")
            }
            Button(
                onClick = {navController.navigate(Screens.NavScreen.name)}
            ){
                Text(text = "Home")
            }

        }
    }

}