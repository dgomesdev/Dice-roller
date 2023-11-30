package com.example.diceroller

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import kotlin.random.Random

const val TAG = "VIEWMODEL TEST"

class DiceRollerViewModelTest {

    private lateinit var diceRollerViewModel: DiceRollerViewModel

    @Test
    fun rollDie_getRandomNumber() {
        // Given
        diceRollerViewModel = DiceRollerViewModel()
        val originalValue = diceRollerViewModel.randomDieNumber

        //When
        diceRollerViewModel.setDieNumber()
        val newValue = diceRollerViewModel.randomDieNumber

        //Then
        assertNotEquals(originalValue, newValue)
    }

    @Test
    fun setDieImage() {
        //Given
        diceRollerViewModel = DiceRollerViewModel()
        val originalImage = diceRollerViewModel.dieImage.value

        //WHen
        diceRollerViewModel.setDieImage()
        val newImage = diceRollerViewModel.dieImage.value

        //Then
        assertNotEquals(originalImage, newImage)
    }

    @Test
    fun checkGuessedNumber_continuesFLowWithoutException() {
        //GIven
        diceRollerViewModel = DiceRollerViewModel()
        val fakeGuessedNumber = Random.nextInt()

        //When
        diceRollerViewModel.checkGuessedNumber("$fakeGuessedNumber")

        //Then
        assertEquals(false, diceRollerViewModel.hasWon.value)
    }

    @Test
    fun checkGuessedNUmber_throwException() {
        //GIven
        diceRollerViewModel = DiceRollerViewModel()
        val fakeGuessedNumber = ""

        //When
        diceRollerViewModel.checkGuessedNumber(fakeGuessedNumber)

        //Then
        assertEquals(null, diceRollerViewModel.hasWon.value)
    }

    @Test
    fun compareNumbers_returnValidNumber() {
        //Given
        diceRollerViewModel = DiceRollerViewModel()
        val fakeGuessedNumber = 1
        val fakeRandomDieNumber = 1

        //When
        val isValid = diceRollerViewModel.validateNumber(fakeGuessedNumber, fakeRandomDieNumber)

        //Then
        assertEquals(true, isValid)
    }

    @Test
    fun compareNumbers_returnInvalidNumber() {
        //GIven
        diceRollerViewModel = DiceRollerViewModel()
        val fakeGuessedNumber = 7
        val fakeRandomDieNumber = 1

        //When
        val isValid = diceRollerViewModel.validateNumber(fakeGuessedNumber, fakeRandomDieNumber)

        //Then
        assertEquals(false, isValid)
    }
}