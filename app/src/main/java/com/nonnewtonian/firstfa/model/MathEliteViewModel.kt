package com.nonnewtonian.firstfa.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nonnewtonian.firstfa.data.HighScore
import com.nonnewtonian.firstfa.repository.MathEliteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

// Holds all score data & preferences, stores & updates DB through repository

@HiltViewModel
class MathEliteViewModel @Inject constructor(private val repository: MathEliteRepository) :
    ViewModel() {

    suspend fun getHighScore(highScore: HighScore): HighScore = repository.getHighScore(highScore)


    //current problem: Understand coroutines, to create asynchronous DB references/use data
    fun checkIfHighScore(highScore: HighScore): Boolean {
        var isHighScore = false
        viewModelScope.launch {
            val oldHighScore = async { getHighScore(highScore) }
            if (oldHighScore.await().score < highScore.score) {
                isHighScore = true
            }
        }
        return isHighScore
    }

    fun updateHighScore(highScore: HighScore)
    {
        if (checkIfHighScore(highScore)) {
            viewModelScope.launch { repository.updateHighScore(highScore) }
        }
    }
}