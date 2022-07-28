package com.nonnewtonian.firstfa.buisness

import com.nonnewtonian.firstfa.model.Question
import com.nonnewtonian.firstfa.model.TrainingType

class QuestionFactory {

    fun generateQuestion(trainingType: TrainingType): Question {
        val inputs = generateInputs(0)
        val wrongAnswers = generateWrongAnswers(trainingType, inputs, 0)
        val correctAnswer = generateCorrectAnswer(trainingType, inputs)
        return Question(trainingType,inputs, wrongAnswers, correctAnswer)
    }

    private fun generateCorrectAnswer(trainingType: TrainingType, inputs: List<Int>): Int {
        return when(trainingType) {
            TrainingType.Addition -> inputs[0] + inputs[1]
            TrainingType.Subtraction -> inputs[0] - inputs[1]
            TrainingType.Division -> inputs[0] / inputs[1]
            TrainingType.Multiplication -> inputs[0] * inputs[1]
        }
    }

    private fun generateWrongAnswers(trainingType: TrainingType, inputs: List<Int>, difficulty: Int = 0): List<Int> {
//        when {
//            difficulty == 0 -> val ra = range(1..10)
//        }
//        TODO("assign random param generation based off difficulty")
        val a = List(3) {
            if(it % 2 == 0 && inputs[0] > 2){
                inputs[0] - (1 until inputs[0]).random()
            }
             else if(difficulty < 3) {
                inputs[0] / (1..2).random() + (1..10).random()
            } else {
                inputs[0] / (1..2).random() + (1..30).random()
            }
        }
        return when(trainingType) {
            TrainingType.Multiplication -> a.map {it * inputs[1]}
            TrainingType.Addition -> a.map {it + inputs[1]}
            TrainingType.Division -> a.map {it / inputs[1]}
            TrainingType.Subtraction -> a.map { it - inputs[1] }
        }
    }

    private fun generateInputs(difficulty: Int = 0): List<Int> {
        //TODO("create difficulty adjustment")
//        TODO("fix difficulty bug at low levels, to assure no doubled answers")
        return when (difficulty) {
            0 -> List(2) { (1..10).random() }
            1 -> List(2) { (1..20).random() }
            2 -> List(2) { (1..30).random() }
            3 -> List(2) { (10..40).random() }
            else -> List(2) { (20..9000).random() }
        }
    }
}