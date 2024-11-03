package com.lgbtqspacey.admin

import admin_portal.composeapp.generated.resources.Res
import admin_portal.composeapp.generated.resources.`continue`
import admin_portal.composeapp.generated.resources.email_or_username
import admin_portal.composeapp.generated.resources.password
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.lgbtqspacey.admin.backend.router.Auth
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var password by remember { mutableStateOf("") }
        var emailOrUsername by remember { mutableStateOf("") }
        val coroutineScope = rememberCoroutineScope()
        var showPassword by remember { mutableStateOf(false) }

        val tryLogin: () -> Unit = {
            coroutineScope.launch {
                Auth().login(password, emailOrUsername)
            }
        }

        Box(
            Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column {
                OutlinedTextField(
                    value = emailOrUsername,
                    onValueChange = { emailOrUsername = it },
                    label = { Text(stringResource(Res.string.email_or_username)) },
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(stringResource(Res.string.password)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                )

                Button(
                    onClick = { tryLogin() },
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    Text(stringResource(Res.string.`continue`))
                }
            }
        }
    }
}