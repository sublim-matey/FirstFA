package com.nonnewtonian.firstfa.ui


import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nonnewtonian.firstfa.model.Quiz
import com.nonnewtonian.firstfa.model.TrainingType

const val TAG = "TrainingScreen"

@Composable
fun TrainingScreen(
    trainingType: TrainingType,
    modifier: Modifier = Modifier
) {

    Log.d(TAG, "TrainingScreen composed")
    val configuration = LocalConfiguration.current
    Surface(
        modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        val quiz by rememberSaveable {
            mutableStateOf(Quiz(trainingType))
        }
        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> LandscapeMode(
                trainingType = trainingType,
                quiz = quiz,
                modifier = modifier
            )
            else -> PortraitMode(
                trainingType = trainingType,
                quiz = quiz,
                modifier = modifier
            )
        }

    }
}

@Composable
fun LandscapeMode(
    trainingType: TrainingType,
    quiz: Quiz,
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
            Text(text = "Score is ${quiz.score}")
            QuestionText(
                text = quiz.currentQuestion.inputs[0].toString(),
                modifier.padding(top = 40.dp)
            )

            Image(
                painter = painterResource(id = trainingType.symbol),
                contentDescription = trainingType.symbolDescription
            )

            QuestionText(
                text = quiz.currentQuestion.inputs[1].toString(),
                modifier.padding(bottom = 40.dp)
            )
        }

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxHeight()
        ) {
            AnswerButton(text = quiz.currentQuestion.answerOrder[0].toString(), quiz = quiz)
            AnswerButton(text = quiz.currentQuestion.answerOrder[1].toString(), quiz = quiz)
            AnswerButton(text = quiz.currentQuestion.answerOrder[2].toString(), quiz = quiz)
            AnswerButton(
                text = quiz.currentQuestion.answerOrder[3].toString(),
                modifier = modifier.padding(bottom = 40.dp),
                quiz = quiz
            )

        }
    }
}

@Composable
fun PortraitMode(
    trainingType: TrainingType,
    quiz: Quiz,
    modifier: Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = "Score is ${quiz.score}")
        QuestionText(
            text = quiz.currentQuestion.inputs[0].toString(),
            modifier.padding(top = 40.dp)
        )

        Image(
            painter = painterResource(id = trainingType.symbol),
            contentDescription = trainingType.symbolDescription
        )

        QuestionText(
            text = quiz.currentQuestion.inputs[1].toString(),
            modifier.padding(bottom = 40.dp)
        )
        AnswerButton(text = quiz.currentQuestion.answerOrder[0].toString(), quiz = quiz)
        AnswerButton(text = quiz.currentQuestion.answerOrder[1].toString(), quiz = quiz)
        AnswerButton(text = quiz.currentQuestion.answerOrder[2].toString(), quiz = quiz)
        AnswerButton(
            modifier.padding(bottom = 40.dp),
            quiz,
            text = quiz.currentQuestion.answerOrder[3].toString()
        )
    }
}

@Composable
fun AnswerButton(
    modifier: Modifier = Modifier,
    quiz: Quiz,
    text: String
) {
    Button(
        onClick = {
            Log.d(TAG, "Button Pressed")
            quiz.recievePlayerAnswer(text.toInt())
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