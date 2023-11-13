package com.example.diceroller

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import java.lang.Double.parseDouble

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    diceImage: Int,
    onClickRollDiceButton: () -> Unit,
    checkGuessedNumber: (Int) -> Unit,
    hasWon: Boolean?,
    onError: (Boolean?) -> Unit
) {
    var guessedNumber by remember {
        mutableStateOf("")
    }
    var openGuessDialog by remember {
        mutableStateOf(false)
    }
    var isError by remember {
        mutableStateOf(false)
    }

    fun checkIfNumber(text: String) {
        try {
            parseDouble(text)
        } catch (e: NumberFormatException) {
            isError = true
            onError(null)
            openGuessDialog  = true
        }
    }

    fun isNumberValid(number: Int) {
        if (number < 1 || number > 6){
            isError = true
            onError(null)
            openGuessDialog = true
        }
    }

    Column(
        modifier.fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        if (openGuessDialog) {
            GuessDialog(
                onDismissRequest = { openGuessDialog = false },
                onConfirmation = { openGuessDialog = false },
                hasWon = hasWon
            )
        }
        Text("Try to guess the number", modifier = modifier)
        TextField(
            value = guessedNumber,
            onValueChange = { guessedNumber = it },
            modifier = modifier,
            isError = isError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    checkIfNumber(guessedNumber)
                    isNumberValid(guessedNumber.toInt())
                    onClickRollDiceButton()
                    checkGuessedNumber(guessedNumber.toInt())
                    openGuessDialog = true
                }
            )
        )
        Image(
            painter = painterResource(diceImage),
            contentDescription = "Dice",
            modifier = modifier
        )
        Button(
            onClick = {
                checkIfNumber(guessedNumber)
                isNumberValid(guessedNumber.toInt())
                onClickRollDiceButton()
                checkGuessedNumber(guessedNumber.toInt())
                openGuessDialog = true
                      },
            modifier = modifier,
            content = { Text("Roll dice") }
        )
    }
}