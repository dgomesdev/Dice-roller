package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.Random

class MainActivity : ComponentActivity() {

    private var randomDieNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            var dieImage by rememberSaveable {
                mutableIntStateOf(R.drawable.empty_dice)
            }

            var hasWon: Boolean? by remember {
                mutableStateOf(false)
            }

            MainScreen(
                Modifier.padding(8.dp),
                diceImage = dieImage,
                onClickRollDiceButton = {
                    rollDice()
                    dieImage = setDieImage(randomDieNumber)
                    hasWon = checkGuessedNumber(it)
                },
                hasWon = hasWon
            )
        }
    }

    private fun rollDice() {
        randomDieNumber = Random().nextInt(6) + 1
    }

    private fun setDieImage(dieNumber: Int) = when (dieNumber) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    private fun checkGuessedNumber(
        guessedNumber: String,
        dieNumber: Int = randomDieNumber,
    ): Boolean? {
        return try {
            val number = guessedNumber.toInt()
            validateNumber(number, dieNumber)
        } catch (e: NumberFormatException) {
            null
        }
    }

    private fun validateNumber(guessedNumber: Int, dieNumber: Int): Boolean? {
        return if (guessedNumber < 1 || guessedNumber > 6) null else guessedNumber == dieNumber
    }
}