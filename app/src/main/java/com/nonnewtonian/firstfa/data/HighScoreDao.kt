package com.nonnewtonian.firstfa.data

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.nonnewtonian.firstfa.model.TrainingType

@Dao
interface HighScoreDao {

//    @Query("SELECT * from high_score_table")
//    suspend fun getHighScores(): List<HighScore>

    @Query("SELECT * FROM high_score_table where time =:time and trainingType =:trainingType and level =:level")
    suspend fun getQuizHighScore(time: Int, trainingType: TrainingType, level: Int): HighScore

    @Insert(onConflict = REPLACE)
    suspend fun insert(highScore: HighScore)

    @Update(onConflict = REPLACE)
    suspend fun update(highScore: HighScore)

    @Query("DELETE from high_score_table")
    suspend fun deleteAll()
}
