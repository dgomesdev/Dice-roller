package com.example.diceroller

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun GuessDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    hasWon: Boolean?
) {
    AlertDialog(
        icon = {
            Icon(
                if (hasWon == true) Icons.Outlined.ThumbUp else Icons.Outlined.Clear,
                contentDescription = "Example Icon")
        },
        title = {
            Text(text = if (hasWon == true) "Congratulations" else "Better luck next time")
        },
        text = {
            Text(text = if (hasWon == true) "You guessed right" else "You guessed wrong")
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("OK")
            }
        }
    )
}