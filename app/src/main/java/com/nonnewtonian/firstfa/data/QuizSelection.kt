package com.nonnewtonian.firstfa.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nonnewtonian.firstfa.model.TrainingType

@Entity(tableName = "quiz_selection_table")
data class QuizSelection(
    @PrimaryKey
    val trainingType: TrainingType,
    val level: Int,
    val time: Int
)
