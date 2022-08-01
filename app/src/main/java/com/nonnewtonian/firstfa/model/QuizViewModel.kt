package com.nonnewtonian.firstfa.model


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nonnewtonian.firstfa.buisness.QuestionFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


const val TAG = "QUIZ_MODEL"
val questionFactory = QuestionFactory()

class QuizViewModel(
    private var trainingType: TrainingType = TrainingType.Multiplication,
    private val _score: Int = 0,
    private val _currentQuestion: Question = questionFactory.generateQuestion(trainingType),
    private val _quizLength: Int = 1
    ): ViewModel() {

    var score by mutableStateOf(_score)
    var currentQuestion by mutableStateOf(_currentQuestion)
    private var quizLength = when (_quizLength) {
        1 -> 60F
        2 -> 120F
        3 -> 180F
        4 -> 240F
        else -> 300F
    }

    val quizTime: Flow<Float> = flow {
        var secondsPassed = quizTimeInit.value
        while (quizLength != secondsPassed.toFloat()) {
            kotlinx.coroutines.delay(1000)
            secondsPassed++
            quizTimeInit.value = secondsPassed
            quizTimeComposeInit.value = secondsPassed / quizLength
            emit(secondsPassed / quizLength)
        }
    }

    // Tracks init value, so when quizTime is called on recomposition/Rotation
    // It remembers old value
    private var quizTimeInit = mutableStateOf(0)

    // Init value for Composable
    var quizTimeComposeInit = mutableStateOf(0.0F)


    fun recievePlayerAnswer(answer: Int) {
        Log.d(TAG, "Recieved Answer")
        if (answer == currentQuestion.correctAnswer) {
            Log.d(TAG, "recievePlayerAnswer called")
            currentQuestion = questionFactory.generateQuestion(trainingType)
            score++

        } else {
            Log.d(TAG, "Question is revamped")
            currentQuestion = questionFactory.generateQuestion(trainingType)

        }
    }

    fun startQuiz(trainingType: TrainingType) {
        this.trainingType = trainingType
    }

}
