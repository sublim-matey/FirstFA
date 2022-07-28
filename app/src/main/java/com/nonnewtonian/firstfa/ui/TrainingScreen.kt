package com.nonnewtonian.firstfa.ui


import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nonnewtonian.firstfa.model.QuizViewModel
import com.nonnewtonian.firstfa.model.TrainingType

const val TAG = "TrainingScreen"

@Composable
fun TrainingScreen(
    trainingType: TrainingType,
    quizViewModel: QuizViewModel,
    modifier: Modifier = Modifier
) {
    //Changes quiz training type from default, sidesteps injection needs
    quizViewModel.startQuiz(trainingType)

    Log.d(TAG, "TrainingScreen composed")
    val configuration = LocalConfiguration.current
    Surface(
        modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        // Hoist this mutablestate into view model

        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> LandscapeMode(
                trainingType = trainingType,
                quizViewModel = quizViewModel,
                modifier = modifier
            )
            else -> PortraitMode(
                trainingType = trainingType,
                quizViewModel = quizViewModel,
                modifier = modifier
            )
        }

    }
}

@Composable
fun LandscapeMode(
    trainingType: TrainingType,
    quizViewModel: QuizViewModel,
    modifier: Modifier
) {
    //TODO("Make landscape mode")
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            Text(text = "Score is ${quizViewModel.score}")
            QuestionText(
                text = quizViewModel.currentQuestion.inputs[0].toString(),
                modifier.padding(top = 40.dp)
            )

            Image(
                painter = painterResource(id = trainingType.symbol),
                contentDescription = trainingType.symbolDescription
            )

            QuestionText(
                text = quizViewModel.currentQuestion.inputs[1].toString(),
                modifier.padding(bottom = 40.dp)
            )
        }

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxHeight()
        ) {
            AnswerButton(text = quizViewModel.currentQuestion.answerOrder[0].toString(), quizViewModel = quizViewModel)
            AnswerButton(text = quizViewModel.currentQuestion.answerOrder[1].toString(), quizViewModel = quizViewModel)
            AnswerButton(text = quizViewModel.currentQuestion.answerOrder[2].toString(), quizViewModel = quizViewModel)
            AnswerButton(
                text = quizViewModel.currentQuestion.answerOrder[3].toString(),
                modifier = modifier.padding(bottom = 40.dp),
                quizViewModel = quizViewModel
            )

        }
    }
}

@Composable
fun PortraitMode(
    trainingType: TrainingType,
    quizViewModel: QuizViewModel,
    modifier: Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = "Score is ${quizViewModel.score}")
        QuestionText(
            text = quizViewModel.currentQuestion.inputs[0].toString(),
            modifier.padding(top = 40.dp)
        )

        Image(
            painter = painterResource(id = trainingType.symbol),
            contentDescription = trainingType.symbolDescription
        )

        QuestionText(
            text = quizViewModel.currentQuestion.inputs[1].toString(),
            modifier.padding(bottom = 40.dp)
        )
        AnswerButton(text = quizViewModel.currentQuestion.answerOrder[0].toString(), quizViewModel = quizViewModel)
        AnswerButton(text = quizViewModel.currentQuestion.answerOrder[1].toString(), quizViewModel = quizViewModel)
        AnswerButton(text = quizViewModel.currentQuestion.answerOrder[2].toString(), quizViewModel = quizViewModel)
        AnswerButton(
            modifier.padding(bottom = 40.dp),
            quizViewModel,
            text = quizViewModel.currentQuestion.answerOrder[3].toString()
        )
    }
}

@Composable
fun AnswerButton(
    modifier: Modifier = Modifier,
    quizViewModel: QuizViewModel,
    text: String
) {
    Button(
        onClick = {
            Log.d(TAG, "Button Pressed")
            quizViewModel.recievePlayerAnswer(text.toInt())
            //quiz = quiz
        },
        modifier = modifier
            .height(60.dp)
            .width(280.dp),
        border = BorderStroke(2.dp, Color.Black),


        ) {
        Text(text = text, fontSize = 32.sp)
    }
}

@Composable
fun QuestionText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 40.sp,
        modifier = modifier
            .padding(all = 5.dp)
            .wrapContentSize()
    )
}