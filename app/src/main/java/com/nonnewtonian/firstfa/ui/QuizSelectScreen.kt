package com.nonnewtonian.firstfa.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nonnewtonian.firstfa.model.QuizSelectViewModel
import com.nonnewtonian.firstfa.model.QuizViewModel

@Composable
fun QuizSelect(
    navController: NavController,
    quizSelectViewModel: QuizSelectViewModel,
    modifier: Modifier = Modifier
        .fillMaxSize()
) {
    Text("Level Select")
    LevelSelect(quizSelectViewModel)
}

@Composable
fun LevelSelect(quizSelectViewModel: QuizSelectViewModel) {
    val options = quizSelectViewModel.levelSelectOptions
    val onSelectionChange = { text: String ->
        quizSelectViewModel.selectedLevelOption = text
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxSize(),
    ) {
        options.forEach { text ->
            Row(
                modifier = Modifier
                    .padding(
                        all = 8.dp,
                    ),
            ) {
                Text(
                    text = text,
                    style = typography.body1.merge(),
                    color = Color.White,
                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(
                                size = 12.dp,
                            ),
                        )
                        .clickable {
                            onSelectionChange(text)
                        }
                        .background(
                            if (text == quizSelectViewModel.selectedLevelOption) {
                                Color.Magenta
                            } else {
                                Color.Gray
                            }
                        )
                        .padding(
                            vertical = 12.dp,
                            horizontal = 16.dp,
                        ),
                )
            }
        }
    }
}
