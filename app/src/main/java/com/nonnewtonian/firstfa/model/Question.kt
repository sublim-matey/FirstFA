package com.nonnewtonian.firstfa.model

data class Question(
    val trainingType: TrainingType,
    val inputs: List<Int>,
    val incorrectAnswers: List<Int>,
    val correctAnswer: Int
) {
    val answerOrder = (incorrectAnswers + correctAnswer).shuffled()
}