package com.nonnewtonian.firstfa.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nonnewtonian.firstfa.model.TrainingType

//@Preview
//@Composable
//fun MultiplicationButtonPreview(){
//    MultiplicationButton()
//}

@Composable
fun MultiplicationButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
        .padding(top = 20.dp)
){
    Button(
        onClick = onClick
    ) {
        Text(text = "Multiplication")
    }
    //TODO("Add symbols")
}

@Composable
fun DivisionButton(
    modifier: Modifier = Modifier
){
    Button(
        onClick = { /*TODO*/ }
    ) {
        Text(text = "Division")
    }
}

@Composable
fun AdditionButton(
    modifier: Modifier = Modifier
) {
    Button(onClick = { /*TODO*/ }) {
        Text(text = "Addition")
    }
}

@Composable
fun SubtractionButton(
    modifier: Modifier = Modifier
) {
    Button(onClick = { /*TODO*/ }) {
        Text(text = "Subtraction")
    }
}