package com.example.diceroller

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun GuessDialog(
    onDismissRequest: () -> Unit,
    hasWon: Boolean?,
) {
    AlertDialog(
        icon = {
            Icon(
                if (hasWon == true) Icons.Outlined.ThumbUp else Icons.Outlined.Clear,
                contentDescription = "Example Icon"
            )
        },
        title = {
            Text(
                when (hasWon) {
                    true -> "Congratulations"
                    false -> "Better luck next time"
                    else -> "Type a number from 1 to 6"
                }
            )
        },
        text = {
            Text(
                when (hasWon) {
                    true -> "You guessed right"
                    false -> "You guessed wrong"
                    else -> "Invalid input"
                },
                Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = { onDismissRequest() },
                Modifier.fillMaxWidth()
            ) {
                Text("OK", textAlign = TextAlign.Center)
            }

        }
    )
}