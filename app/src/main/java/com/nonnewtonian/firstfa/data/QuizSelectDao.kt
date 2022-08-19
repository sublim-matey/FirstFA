package com.nonnewtonian.firstfa.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.nonnewtonian.firstfa.model.TrainingType

@Dao
interface QuizSelectDao {

    @Query("SELECT * FROM quiz_selection_table where trainingType =:trainingType")
    suspend fun getQuizSelection(trainingType: TrainingType): QuizSelection

    @Insert(onConflict = REPLACE)
    suspend fun insert(quizSelection: QuizSelection)

    @Update(onConflict = REPLACE)
    suspend fun update(quizSelection: QuizSelection)

    @Query("DELETE From quiz_selection_table")
    suspend fun deleteAll()
}