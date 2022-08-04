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


@Composable
fun MathEliteNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.NavScreen.name){
        composable(Screens.NavScreen.name) {
            NavScreen(navController = navController)
        }

        // Need to figure out how to centralize Repository in other screens
        composable(Screens.TrainingScreen.name ) {
            Log.d("nav", "TrainingScreen navigate called")
            val quizViewModel = hiltViewModel<QuizViewModel>()
            Log.d("nav", "quizViewModel created")
            TrainingScreen(navController = navController, quizViewModel = quizViewModel)
        }

    }
}