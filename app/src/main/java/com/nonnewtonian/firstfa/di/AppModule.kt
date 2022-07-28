package com.nonnewtonian.firstfa.di

import android.content.Context
import androidx.room.Room
import com.nonnewtonian.firstfa.data.MathEliteDao
import com.nonnewtonian.firstfa.data.MathEliteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun providesMathEliteDao(mathEliteDatabase: MathEliteDatabase): MathEliteDao =
        mathEliteDatabase.mathEliteDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): MathEliteDatabase =
        Room.databaseBuilder(
            context,
            MathEliteDatabase::class.java,
            "math_elite_db"
            ).fallbackToDestructiveMigration()
            .build()
}