package com.nonnewtonian.firstfa.model


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nonnewtonian.firstfa.buisness.QuestionFactory
import com.nonnewtonian.firstfa.repository.MathEliteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


const val TAG = "QUIZ_MODEL"
val questionFactory = QuestionFactory()

@HiltViewModel
class QuizViewModel @Inject constructor(
    _mathEliteRepository: MathEliteRepository
) : ViewModel() {

    init {
        Log.d("nav", "QuizViewModel init")
    }

    private var _trainingType: TrainingType = _mathEliteRepository.getTrainingType()
    private val _score: Int = 0
    private val _currentQuestion: Question = questionFactory.generateQuestion(_trainingType)
    private val _quizLength: Int = 1

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
            currentQuestion = questionFactory.generateQuestion(_trainingType)
            score++

        } else {
            Log.d(TAG, "Question is revamped")
            currentQuestion = questionFactory.generateQuestion(_trainingType)

        }
    }

    fun startQuiz(trainingType: TrainingType) {
        this._trainingType = trainingType
    }

    fun getTrainingType(): TrainingType = _trainingType

}
