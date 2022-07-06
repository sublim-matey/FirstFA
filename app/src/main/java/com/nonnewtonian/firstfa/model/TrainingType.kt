package com.nonnewtonian.firstfa.model

import com.nonnewtonian.firstfa.R

enum class TrainingType(
    val value: String,
    val symbol: Int,
    val symbolDescription: String
) {
    Multiplication(
        value = "Multiplication",
        symbol = R.drawable.ic_multiplication,
        symbolDescription = "Multiplication symbol"
    ),

    Division(
        value = "Division",
        symbol = R.drawable.ic_division,
        symbolDescription = "Division symbol"
    ),

    Addition(
        value = "Addition",
        symbol = R.drawable.ic_addition,
        symbolDescription = "Plus symbol"
    ),

    Subtraction(
        value = "Subtraction",
        symbol = R.drawable.ic_subtraction,
        symbolDescription = "Minus symbol"
    )
}