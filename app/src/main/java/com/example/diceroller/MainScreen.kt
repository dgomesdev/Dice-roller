package com.example.diceroller

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen(
    modifier: Modifier,
    diceImage: Int,
    onClickRollDiceButton: (String) -> Unit,
    hasWon: Boolean?
) {
    var guessedNumber by remember {
        mutableStateOf("")
    }

    var openGuessDialog by remember {
        mutableStateOf(false)
    }

    Column(
        modifier.fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        if (openGuessDialog) {
            GuessDialog(
                onDismissRequest = { openGuessDialog = false },
                hasWon = hasWon
            )
        }
        Text("Try to guess the number", modifier = modifier.padding(8.dp), fontSize = 24.sp)
        TextField(
            value = guessedNumber,
            onValueChange = { guessedNumber = it },
            modifier = modifier,
            isError = hasWon == null,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Go
            ),
            keyboardActions = KeyboardActions(
                onGo = {
                    onClickRollDiceButton(guessedNumber)
                    openGuessDialog = true
                    guessedNumber = ""
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
                onClickRollDiceButton(guessedNumber)
                openGuessDialog = true
                guessedNumber = ""
                      },
            modifier = modifier,
            content = { Text("Roll dice") }
        )
    }
}