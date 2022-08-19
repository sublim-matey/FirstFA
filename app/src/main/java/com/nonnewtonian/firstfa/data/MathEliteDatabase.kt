package com.nonnewtonian.firstfa.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HighScore::class, QuizSelection::class], version = 1, exportSchema = false)
abstract class MathEliteDatabase: RoomDatabase() {
    abstract fun mathEliteDao(): HighScoreDao
    abstract fun quizSelectDao(): QuizSelectDao
}