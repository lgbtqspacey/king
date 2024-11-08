package com.lgbtqspacey.admin.features.auth

import admin_portal.composeapp.generated.resources.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.lgbtqspacey.admin.backend.adapter.AuthAdapter
import com.lgbtqspacey.admin.getPlatform
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun Login() {
    MaterialTheme {
        val coroutineScope = rememberCoroutineScope()

        var password by remember { mutableStateOf("") }
        var username by remember { mutableStateOf("") }
        var isPasswordVisible by remember { mutableStateOf(false) }
        var isError by remember { mutableStateOf(false) }

        val passwordVisualTransformation: VisualTransformation?
        val passwordToggleImage: ImageVector?
        val passwordToggleDescription: String?

        val tryLogin: () -> Unit = {
            if (password.isEmpty() || username.isEmpty()) {
                isError = true
            } else {
                coroutineScope.launch {
                    val response = AuthAdapter().login(username, password)
                    println("login response: $response")
                }
            }
        }

        if (isPasswordVisible) {
            passwordVisualTransformation = VisualTransformation.None
            passwordToggleImage = vectorResource(Res.drawable.ic_visibility_off)
            passwordToggleDescription = stringResource(Res.string.hide_password)
        } else {
            passwordVisualTransformation = PasswordVisualTransformation()
            passwordToggleImage = vectorResource(Res.drawable.ic_visibility)
            passwordToggleDescription = stringResource(Res.string.show_password)
        }

        Box(
            Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier.padding(top = 80.dp)
            ) {
                Text(text = stringResource(Res.string.log_into_account))

                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text(stringResource(Res.string.username)) },
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(stringResource(Res.string.password)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = passwordVisualTransformation,
                    trailingIcon = {
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(passwordToggleImage, passwordToggleDescription)
                        }
                    }
                )

                Button(
                    onClick = { tryLogin() },
                    contentPadding = PaddingValues(horizontal = 16.dp),
                ) {
                    Text(stringResource(Res.string.`continue`))
                }

                Text(text = stringResource(Res.string.problems_to_log_in))
                Text(text = stringResource(Res.string.open_a_ticket))

                Text(text = "v${getPlatform().version}")
            }
        }
    }
}
