package com.lgbtqspacey.king.features.composable

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Dialog(
    titleText: String,
    subtitleText: String,
    dismissBtnText: String,
    confirmBtnText: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    Dialog(
        titleText = Text(
            text = titleText,
            color = MaterialTheme.colors.onSurface,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        ).toString(),
        subtitleText = Text(
            text = subtitleText,
            color = MaterialTheme.colors.onSurface,
            textAlign = TextAlign.Center,
        ).toString(),
        dismissBtnText = dismissBtnText,
        confirmBtnText = confirmBtnText,
        onDismiss = onDismiss,
        onConfirm = onConfirm,
    )
}

@Composable
fun ErrorDialog(
    titleText: String,
    errorMessage: String,
    btnText: String,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        backgroundColor = MaterialTheme.colors.error,
        title = {
            Text(
                text = titleText,
                color = MaterialTheme.colors.onError,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        },
        text = {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.onError,
                textAlign = TextAlign.Center,
            )
        },
        buttons = {
            Button(
                onClick = { onDismiss() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.error,
                    contentColor = MaterialTheme.colors.onError
                )
            ) {
                Text(btnText)
            }
        },
    )
}
