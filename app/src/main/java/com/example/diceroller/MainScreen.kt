package com.example.diceroller

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(
    diceImage: Int,
    onClickRollDiceButton: () -> Unit,
) {
    Column(
        Modifier.padding(16.dp).fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(diceImage),
            contentDescription = "Dice",
            Modifier.padding(8.dp))
        Button(
            onClick = { onClickRollDiceButton() },
            Modifier.padding(8.dp),
            content = { Text("Roll dice") }
        )
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(diceImage = R.drawable.ic_dice) {}
}