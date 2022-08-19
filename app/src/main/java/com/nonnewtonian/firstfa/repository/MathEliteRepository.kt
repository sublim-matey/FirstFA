package com.nonnewtonian.firstfa.repository

import com.nonnewtonian.firstfa.data.HighScoreDao
import com.nonnewtonian.firstfa.data.HighScore
import com.nonnewtonian.firstfa.data.QuizSelectDao
import com.nonnewtonian.firstfa.data.QuizSelection
import com.nonnewtonian.firstfa.model.TrainingType
import javax.inject.Inject

class MathEliteRepository @Inject constructor(
    private val highScoreDao: HighScoreDao,
    private val quizSelectDao: QuizSelectDao
    ) {


    // TODO:("Set trainingtype through button click on other composable, when making trainingScreen")
    private var _trainingType: TrainingType = TrainingType.Multiplication

    // high_score_table functions
    suspend fun addHighScore(highScore: HighScore) = highScoreDao.insert(highScore)
    suspend fun updateHighScore(highScore: HighScore) = highScoreDao.update(highScore)
    suspend fun deleteAllHighScore() = highScoreDao.deleteAll()
    suspend fun getHighScore(highScore: HighScore): HighScore {
        return highScoreDao.getQuizHighScore(
            highScore.time,
            highScore.trainingType,
            highScore.level
        )
    }

    // quizselection table functions
    suspend fun addQuizSelection(quizSelection: QuizSelection) = quizSelectDao.insert(quizSelection)
    suspend fun updateQuizSelection(quizSelection: QuizSelection) = quizSelectDao.update(quizSelection)
    suspend fun deleteAllQuizSelection() = quizSelectDao.deleteAll()
    suspend fun getQuizSelection(trainingType: TrainingType): QuizSelection {
        return quizSelectDao.getQuizSelection(
            trainingType
        )
    }





    fun putTrainingType(trainingType: TrainingType){
        _trainingType = trainingType
    }

    fun getTrainingType(): TrainingType {
        return _trainingType
    }


}