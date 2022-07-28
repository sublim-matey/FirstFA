package com.nonnewtonian.firstfa.model

import androidx.room.Entity

@Entity(tableName = "high_score_table", primaryKeys = ["trainingType", "time", "level"])
data class HighScore(
    val trainingType: TrainingType,
    val score: Int,
    val time: Int,
    val level: Int
)
