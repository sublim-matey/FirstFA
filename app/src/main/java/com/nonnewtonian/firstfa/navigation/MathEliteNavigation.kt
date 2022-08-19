package com.nonnewtonian.firstfa.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nonnewtonian.firstfa.model.QuizViewModel
import com.nonnewtonian.firstfa.ui.NavScreen
import com.nonnewtonian.firstfa.ui.TrainingScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.nonnewtonian.firstfa.model.QuizSelectViewModel
import com.nonnewtonian.firstfa.ui.QuizSelect
import com.nonnewtonian.firstfa.ui.ScoreScreen


@Composable
fun MathEliteNavigation() {
    val navController = rememberNavController()
    var quizViewModel = hiltViewModel<QuizViewModel>()
    val quizSelectViewModel = hiltViewModel<QuizSelectViewModel>()
    NavHost(navController = navController, startDestination = Screens.NavScreen.name){

        composable(Screens.NavScreen.name) {
            NavScreen(quizSelectViewModel, quizViewModel, navController = navController)
        }

        // Need to figure out how to centralize Repository in other screens
        composable(Screens.TrainingScreen.name ) {
            Log.d("nav", "TrainingScreen navigate called")
            quizViewModel = hiltViewModel<QuizViewModel>()
            Log.d("nav", "quizViewModel created")
            TrainingScreen(navController = navController, quizViewModel = quizViewModel)
        }

        //takes highScore info N passes it to ScoreScreen
        composable(Screens.ScoreScreen.name) {
            val highScore = quizViewModel.getHighScore()
            ScoreScreen( navController = navController, highScore =  highScore)
        }

        //QuizSelectScreen
        composable(Screens.QuizSelectScreen.name){
            QuizSelect(navController = navController, quizSelectViewModel = quizSelectViewModel)
        }

    }
}