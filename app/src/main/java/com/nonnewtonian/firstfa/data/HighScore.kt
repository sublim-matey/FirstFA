package com.nonnewtonian.firstfa.data

import androidx.room.Entity
import com.nonnewtonian.firstfa.model.TrainingType

@Entity(tableName = "high_score_table", primaryKeys = ["trainingType", "time", "level"])
data class HighScore(
    val trainingType: TrainingType,
    val score: Int,
    val time: Int,
    val level: Int
)
