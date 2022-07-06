package com.nonnewtonian.firstfa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nonnewtonian.firstfa.model.TrainingType
import com.nonnewtonian.firstfa.ui.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            AppTheme {
                TrainingScreen(TrainingType.Multiplication)
            }
        }
    }

}