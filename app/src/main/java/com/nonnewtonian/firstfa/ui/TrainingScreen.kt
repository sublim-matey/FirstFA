package com.nonnewtonian.firstfa.ui


import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
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
import androidx.navigation.NavController
import com.nonnewtonian.firstfa.model.QuizViewModel
import com.nonnewtonian.firstfa.navigation.Screens
import kotlinx.coroutines.delay

const val TAG = "TrainingScreen"

@Composable
fun TrainingScreen(
    navController: NavController,
    quizViewModel: QuizViewModel,
    modifier: Modifier = Modifier
) {
    //Changes quiz training type from default, sidesteps injection needs
    // Probably unnecessary,
//    quizViewModel.startQuiz(trainingType)


    Log.d(TAG, "TrainingScreen composed")
    val configuration = LocalConfiguration.current
    Surface(
        modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {

        // Collects one event, to navigate to ScoreScreen at time end
        LaunchedEffect(Unit) {
            quizViewModel.quizOverFlow.collect { event ->
                when (event) {
                    true -> navController.navigate(Screens.ScoreScreen.name)
                    else -> {}
                }
            }
        }

        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> LandscapeMode(

                quizViewModel = quizViewModel,
                modifier = modifier
            )
            else -> PortraitMode(

                quizViewModel = quizViewModel,
                modifier = modifier
            )
        }

    }
}

@Composable
fun LandscapeMode(

    quizViewModel: QuizViewModel,
    modifier: Modifier
) {

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
            ProgressBarTimer(quizViewModel = quizViewModel)
            Text(text = "Score is ${quizViewModel.score}")
            QuestionText(
                text = quizViewModel.currentQuestion.inputs[0].toString(),
                modifier.padding(top = 40.dp)
            )

            Image(
                painter = painterResource(id = quizViewModel.getTrainingType().symbol),
                contentDescription = quizViewModel.getTrainingType().symbolDescription
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
            AnswerButton(
                text = quizViewModel.currentQuestion.answerOrder[0].toString(),
                quizViewModel = quizViewModel
            )
            AnswerButton(
                text = quizViewModel.currentQuestion.answerOrder[1].toString(),
                quizViewModel = quizViewModel
            )
            AnswerButton(
                text = quizViewModel.currentQuestion.answerOrder[2].toString(),
                quizViewModel = quizViewModel
            )
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

    quizViewModel: QuizViewModel,
    modifier: Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ProgressBarTimer(quizViewModel = quizViewModel)
        Text(text = "Score is ${quizViewModel.score}")
        QuestionText(
            text = quizViewModel.currentQuestion.inputs[0].toString(),
            modifier.padding(top = 40.dp)
        )

        Image(
            painter = painterResource(id = quizViewModel.getTrainingType().symbol),
            contentDescription = quizViewModel.getTrainingType().symbolDescription
        )

        QuestionText(
            text = quizViewModel.currentQuestion.inputs[1].toString(),
            modifier.padding(bottom = 40.dp)
        )
        AnswerButton(
            text = quizViewModel.currentQuestion.answerOrder[0].toString(),
            quizViewModel = quizViewModel
        )
        AnswerButton(
            text = quizViewModel.currentQuestion.answerOrder[1].toString(),
            quizViewModel = quizViewModel
        )
        AnswerButton(
            text = quizViewModel.currentQuestion.answerOrder[2].toString(),
            quizViewModel = quizViewModel
        )
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
            quizViewModel.receivePlayerAnswer(text.toInt())
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

@Composable
fun ProgressBarTimer(quizViewModel: QuizViewModel) {

    val time = quizViewModel.quizTime.collectAsState(quizViewModel.quizTimeComposeInit.value)

    LinearProgressIndicator(
        modifier = Modifier,
        progress = time.value
    )
}