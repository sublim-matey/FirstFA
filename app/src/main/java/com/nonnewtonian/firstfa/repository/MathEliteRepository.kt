package com.nonnewtonian.firstfa.repository

import com.nonnewtonian.firstfa.data.MathEliteDao
import com.nonnewtonian.firstfa.model.HighScore
import com.nonnewtonian.firstfa.model.QuizViewModel
import com.nonnewtonian.firstfa.model.TrainingType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class MathEliteRepository @Inject constructor(private val mathEliteDao: MathEliteDao) {


    // TODO:("Set trainingtype through button click on other composable, when making trainingScreen")
    private var _trainingType: TrainingType = TrainingType.Multiplication

    suspend fun addHighScore(highScore: HighScore) = mathEliteDao.insert(highScore)
    suspend fun updateHighScore(highScore: HighScore) = mathEliteDao.update(highScore)
    suspend fun deleteAll() = mathEliteDao.deleteAll()
    suspend fun getHighScore(highScore: HighScore): HighScore {
        return mathEliteDao.getQuizHighScore(
            highScore.time,
            highScore.trainingType,
            highScore.level
        )
    }

    fun putTrainingType(trainingType: TrainingType){
        _trainingType = trainingType
    }

    fun getTrainingType(): TrainingType {
        return _trainingType
    }


}