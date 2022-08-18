package com.nonnewtonian.firstfa.model


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.nonnewtonian.firstfa.buisness.QuestionFactory
import com.nonnewtonian.firstfa.data.HighScore
import com.nonnewtonian.firstfa.repository.MathEliteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


const val TAG = "QUIZ_MODEL"
val questionFactory = QuestionFactory()

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val _mathEliteRepository: MathEliteRepository
) : ViewModel() {

    init {
        Log.d("nav", "QuizViewModel init")
    }

    private var _trainingType: TrainingType = _mathEliteRepository.getTrainingType()
    private val _score: Int = 0
    private val _currentQuestion: Question = questionFactory.generateQuestion(_trainingType)
    private val _quizLength: Int = 1
    private val _level: Int = 1
    private val _quizOver = MutableSharedFlow<Boolean>()




    var score by mutableStateOf(_score)
    var currentQuestion by mutableStateOf(_currentQuestion)
    var quizLength = when (_quizLength) {
        1 -> 10F
        2 -> 60F
        3 -> 120F
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
        _quizOver.emit(true)
    }

    // Tracks when time is up, sends single event to listener to navigate to ScoreScreen
    val quizOverFlow = _quizOver.asSharedFlow()

    // Tracks init value, so when quizTime is called on recomposition/Rotation
    // It remembers old value
    private var quizTimeInit = mutableStateOf(0)

    // Init value for Composable
    var quizTimeComposeInit = mutableStateOf(0.0F)


    fun receivePlayerAnswer(answer: Int) {
        Log.d(TAG, "Received Answer")
        if (answer == currentQuestion.correctAnswer) {
            Log.d(TAG, "receivePlayerAnswer called")
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

    fun getHighScore(): HighScore {
        return HighScore(this._trainingType, this.score, this._quizLength, this._level)
    }
    fun stop() {
        quizTime
    }

    fun getTrainingType(): TrainingType = _trainingType

}
