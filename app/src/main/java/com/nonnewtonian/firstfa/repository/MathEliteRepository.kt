package com.nonnewtonian.firstfa.repository

import com.nonnewtonian.firstfa.data.MathEliteDao
import com.nonnewtonian.firstfa.model.HighScore
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class MathEliteRepository @Inject constructor(private val mathEliteDao: MathEliteDao) {
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
}