package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.diceroller.theme.DiceRollerAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<DiceRollerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val dieImage = viewModel.dieImage.value
            val hasWon = viewModel.hasWon.value
            DiceRollerAppTheme {
                Surface(
                    Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        Modifier.padding(8.dp),
                        diceImage = dieImage,
                        onClickRollDiceButton = {
                            viewModel.setDieNumber()
                            viewModel.setDieImage()
                            viewModel.checkGuessedNumber(it)
                        },
                        hasWon = hasWon
                    )
                }
            }
        }
    }
}