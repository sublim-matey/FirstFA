package com.nonnewtonian.firstfa.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nonnewtonian.firstfa.R
import com.nonnewtonian.firstfa.model.QuizSelectViewModel
import com.nonnewtonian.firstfa.model.QuizViewModel
import com.nonnewtonian.firstfa.model.TrainingType
import com.nonnewtonian.firstfa.navigation.Screens

@Composable
fun NavScreen(
    quizSelectViewModel: QuizSelectViewModel,
    quizViewModel: QuizViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
        .fillMaxWidth()
) {
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

            MultiplicationButton(quizSelectViewModel, navController)
            DivisionButton(quizSelectViewModel, navController)
            AdditionButton(quizSelectViewModel, navController)
            SubtractionButton(quizSelectViewModel, navController)
        }
    }
}

@Composable
fun MultiplicationButton(
    quizSelectViewModel: QuizSelectViewModel,
    navController: NavController,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
        .padding(top = 20.dp)
) {
    Button(
        onClick = {
            quizSelectViewModel.setTrainingType(TrainingType.Multiplication)
            navController.navigate(Screens.QuizSelectScreen.name)
        }
    ) {
        Text(text = "Multiplication")
    }
    //TODO("Add symbols")
}

@Composable
fun DivisionButton(
    quizSelectViewModel: QuizSelectViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {
            quizSelectViewModel.setTrainingType(TrainingType.Division)
            navController.navigate(Screens.QuizSelectScreen.name)
        }
    ) {
        Text(text = "Division")
    }
}

@Composable
fun AdditionButton(
    quizSelectViewModel: QuizSelectViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Button(onClick = {
        quizSelectViewModel.setTrainingType(TrainingType.Addition)
        navController.navigate(Screens.QuizSelectScreen.name)
    }) {
        Text(text = "Addition")
    }
}

@Composable
fun SubtractionButton(
    quizSelectViewModel: QuizSelectViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Button(onClick = {
        quizSelectViewModel.setTrainingType(TrainingType.Subtraction)
        navController.navigate(Screens.QuizSelectScreen.name)
    }) {
        Text(text = "Subtraction")
    }
}


