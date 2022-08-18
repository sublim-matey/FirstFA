package com.nonnewtonian.firstfa.repository

import com.nonnewtonian.firstfa.data.HighScoreDao
import com.nonnewtonian.firstfa.data.HighScore
import com.nonnewtonian.firstfa.model.TrainingType
import javax.inject.Inject

class MathEliteRepository @Inject constructor(private val highScoreDao: HighScoreDao) {


    // TODO:("Set trainingtype through button click on other composable, when making trainingScreen")
    private var _trainingType: TrainingType = TrainingType.Multiplication

    suspend fun addHighScore(highScore: HighScore) = highScoreDao.insert(highScore)
    suspend fun updateHighScore(highScore: HighScore) = highScoreDao.update(highScore)
    suspend fun deleteAll() = highScoreDao.deleteAll()
    suspend fun getHighScore(highScore: HighScore): HighScore {
        return highScoreDao.getQuizHighScore(
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