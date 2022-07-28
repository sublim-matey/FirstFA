package com.nonnewtonian.firstfa.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nonnewtonian.firstfa.model.HighScore

@Database(entities = [HighScore::class], version = 1, exportSchema = false)
abstract class MathEliteDatabase: RoomDatabase() {
    abstract fun mathEliteDao(): MathEliteDao
}