package com.nonnewtonian.firstfa.buisness

import android.util.Log
import com.nonnewtonian.firstfa.model.Question
import com.nonnewtonian.firstfa.model.TrainingType
import kotlin.random.Random

class QuestionFactory {

    fun generateQuestion(trainingType: TrainingType, difficulty: Int = 0): Question {
        val inputs = generateInputs(trainingType, difficulty)
        val correctAnswer = generateCorrectAnswer(trainingType, inputs)
        val wrongAnswers = generateWrongAnswers(trainingType, inputs, difficulty)
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
                    inputs[0] / (1..2).random() + (1..5).random()
                } else {
                    inputs[0] / (1..2).random() + (1..30).random()
                }
            if (a != inputs[0]) {
                wrongAns.add(a)
            }
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
        val random = Random(System.currentTimeMillis())
        random.nextInt(10)

        when (trainingType) {
            TrainingType.Multiplication ->
                return when (difficulty) {
                    0 -> List(2) { random.nextInt(1,10) }
                    1 -> List(2) { random.nextInt(1,20) }
                    2 -> List(2) { random.nextInt(1,30) }
                    3 -> List(2) { random.nextInt(1,50) }
                    else -> List(2) { random.nextInt(20,9999) }
                }
            TrainingType.Division ->
                return when (difficulty) {
                    0 -> List(2) { random.nextInt(1,10) }
                    1 -> List(2) { random.nextInt(1,20) }
                    2 -> List(2) { random.nextInt(1,30) }
                    3 -> List(2) { random.nextInt(1,50) }
                    else -> List(2) { random.nextInt(20,9999) }
                }
            TrainingType.Addition ->
                return when (difficulty) {
                    0 -> List(2) { random.nextInt(1,10) }
                    1 -> List(2) { random.nextInt(10,50) }
                    2 -> List(2) { random.nextInt(50,100) }
                    3 -> List(2) { random.nextInt(100,500) }
                    else -> List(2) { random.nextInt(20,9999) }
                }
            TrainingType.Subtraction ->
                return when (difficulty) {
                    0 -> List(2) { random.nextInt(1,10) }
                    1 -> List(2) { random.nextInt(10,50) }
                    2 -> List(2) { random.nextInt(50,100) }
                    3 -> List(2) { random.nextInt(100,500) }
                    else -> List(2) { random.nextInt(20,9999) }
                }
        }
    }
}