package com.nonnewtonian.firstfa.model

import android.os.Parcelable
import android.util.Log
import com.nonnewtonian.firstfa.buisness.QuestionFactory
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

const val TAG = "QUIZ_MODEL"
val questionFactory = QuestionFactory()
@Parcelize
data class Quiz(
    val trainingType: TrainingType,
    var score: Int = 0
    ): Parcelable {


    @IgnoredOnParcel
    var currentQuestion: Question = questionFactory.generateQuestion(trainingType)

    fun recievePlayerAnswer(answer: Int) {
        Log.d(TAG, "Recieved Answer")
        if (answer == currentQuestion.correctAnswer) {
            Log.d(TAG, "recievePlayerAnswer called")
            score++
            currentQuestion = questionFactory.generateQuestion(trainingType)
        } else {
            Log.d(TAG, "Question is revamped")
            currentQuestion =  questionFactory.generateQuestion(trainingType)
            Log.d(TAG, currentQuestion.correctAnswer.toString())
        }
    }

}
