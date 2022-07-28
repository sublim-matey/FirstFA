package com.nonnewtonian.firstfa.model


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nonnewtonian.firstfa.buisness.QuestionFactory


const val TAG = "QUIZ_MODEL"
val questionFactory = QuestionFactory()

class QuizViewModel(
    private var trainingType: TrainingType = TrainingType.Multiplication,
    private val _score: Int = 0,
    private val _currentQuestion: Question = questionFactory.generateQuestion(trainingType)
    ): ViewModel() {

    var score by mutableStateOf(_score)
    var currentQuestion by mutableStateOf(_currentQuestion)


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
