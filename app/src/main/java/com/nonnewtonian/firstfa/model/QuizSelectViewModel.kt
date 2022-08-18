package com.nonnewtonian.firstfa.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.nonnewtonian.firstfa.repository.MathEliteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizSelectViewModel @Inject constructor(private val repository: MathEliteRepository) :
    ViewModel() {
    val levelSelectOptions = listOf(
        "1",
        "2",
        "3",
        "4",
        "5"
    )
    var selectedLevelOption by mutableStateOf("")

    fun submit() {
        TODO()
    }


}