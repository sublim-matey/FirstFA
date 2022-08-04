package com.nonnewtonian.firstfa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.nonnewtonian.firstfa.model.QuizViewModel
import com.nonnewtonian.firstfa.model.TrainingType
import com.nonnewtonian.firstfa.navigation.MathEliteNavigation
import com.nonnewtonian.firstfa.ui.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val quizViewModel: QuizViewModel  by viewModels()
        setContent{
            AppTheme {
                MathEliteNavigation()
            }
        }
    }

}