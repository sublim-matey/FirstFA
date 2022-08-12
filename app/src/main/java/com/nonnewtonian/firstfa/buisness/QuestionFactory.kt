package com.nonnewtonian.firstfa.buisness

import com.nonnewtonian.firstfa.model.Question
import com.nonnewtonian.firstfa.model.TrainingType

class QuestionFactory {

    fun generateQuestion(trainingType: TrainingType, difficulty: Int = 0): Question {
        val inputs = generateInputs(trainingType, difficulty)
        val wrongAnswers = generateWrongAnswers(trainingType, inputs, difficulty)
        val correctAnswer = generateCorrectAnswer(trainingType, inputs)
        return Question(trainingType, inputs, wrongAnswers, correctAnswer)
    }

    private fun generateCorrectAnswer(trainingType: TrainingType, inputs: List<Int>): Int {
        return when (trainingType) {
            TrainingType.Addition -> inputs[0] + inputs[1]
            TrainingType.Subtraction -> inputs[0] - inputs[1]
            TrainingType.Division -> inputs[0] / inputs[1]
            TrainingType.Multiplication -> inputs[0] * inputs[1]
        }
    }

    private fun generateWrongAnswers(
        trainingType: TrainingType,
        inputs: List<Int>,
        difficulty: Int = 0
    ): List<Int> {

        val wrongAns = mutableSetOf<Int>()
        while (wrongAns.size < 3) {
            val a =
                if (difficulty < 3) {
                    inputs[0] / (1..2).random() + (1..10).random()
                } else {
                    inputs[0] / (1..2).random() + (1..30).random()
                }
            wrongAns.add(a)
        }
        return when (trainingType) {
            TrainingType.Multiplication -> wrongAns.map { it * inputs[1] }
            TrainingType.Addition -> wrongAns.map { it + inputs[1] }
            TrainingType.Division -> wrongAns.map { it / inputs[1] }
            TrainingType.Subtraction -> wrongAns.map { it - inputs[1] }
        }
    }

    private fun generateInputs( trainingType: TrainingType, difficulty: Int = 0): List<Int> {
        //TODO("create difficulty adjustment")
//        TODO("fix difficulty bug at low levels, to assure no doubled answers")
        when (trainingType) {
            TrainingType.Multiplication ->
                return when (difficulty) {
                    0 -> List(2) { (1..10).random() }
                    1 -> List(2) { (1..20).random() }
                    2 -> List(2) { (1..30).random() }
                    3 -> List(2) { (10..40).random() }
                    else -> List(2) { (20..9000).random() }
                }
            TrainingType.Division ->
                return when (difficulty) {
                    0 -> List(2) { (1..10).random() }
                    1 -> List(2) { (1..20).random() }
                    2 -> List(2) { (1..30).random() }
                    3 -> List(2) { (10..40).random() }
                    else -> List(2) { (20..9000).random() }
                }
            TrainingType.Addition ->
                return when (difficulty) {
                    0 -> List(2) { (1..10).random() }
                    1 -> List(2) { (1..50).random() }
                    2 -> List(2) { (1..100).random() }
                    3 -> List(2) { (100..1000).random() }
                    else -> List(2) { (100..9000).random() }
                }
            TrainingType.Subtraction ->
                return when (difficulty) {
                    0 -> List(2) { (1..10).random() }
                    1 -> List(2) { (1..50).random() }
                    2 -> List(2) { (1..100).random() }
                    3 -> List(2) { (100..1000).random() }
                    else -> List(2) { (100..9000).random() }
                }
        }
    }
}