package com.example.diceroller

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class DiceRollerViewModel: ViewModel() {

    var randomDieNumber = 0

    private val _dieImage = mutableIntStateOf(R.drawable.empty_dice)
    val dieImage: State<Int> = _dieImage

    private val _hasWon = mutableStateOf<Boolean?>(false)
    val hasWon: State<Boolean?> = _hasWon

    fun setDieNumber() {
        randomDieNumber = Random.nextInt(1, 6)
    }

    fun setDieImage() {
        _dieImage.intValue = when (randomDieNumber) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }

    fun checkGuessedNumber(guessedNumber: String) {
        try {
            val number = guessedNumber.toInt()
            _hasWon.value = validateNumber(number, randomDieNumber)
        } catch (e: NumberFormatException) {
            _hasWon.value = null
        }
    }

    fun validateNumber(guessedNumber: Int, dieNumber: Int): Boolean {
        return guessedNumber in 1..6 && guessedNumber == dieNumber
    }
}