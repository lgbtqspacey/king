package com.lgbtqspacey.admin.features.auth

import admin_portal.composeapp.generated.resources.Res
import admin_portal.composeapp.generated.resources.`continue`
import admin_portal.composeapp.generated.resources.fill_all_fields
import admin_portal.composeapp.generated.resources.hide_password
import admin_portal.composeapp.generated.resources.ic_visibility
import admin_portal.composeapp.generated.resources.ic_visibility_off
import admin_portal.composeapp.generated.resources.log_into_account
import admin_portal.composeapp.generated.resources.open_a_ticket
import admin_portal.composeapp.generated.resources.password
import admin_portal.composeapp.generated.resources.problems_to_log_in
import admin_portal.composeapp.generated.resources.show_password
import admin_portal.composeapp.generated.resources.username
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.lgbtqspacey.admin.backend.adapter.AuthAdapter
import com.lgbtqspacey.admin.components.Dimensions
import com.lgbtqspacey.admin.getPlatform
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
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
        var showError by remember { mutableStateOf(false) }
        var errorMessage by remember { mutableStateOf("") }

        val passwordVisualTransformation: VisualTransformation?
        val passwordToggleImage: ImageVector?
        val passwordToggleDescription: String?

        val tryLogin: () -> Unit = {
            if (password.isEmpty() || username.isEmpty()) {
                coroutineScope.launch {
                    errorMessage = getString(Res.string.fill_all_fields)
                    showError = true
                }
            } else {
                coroutineScope.launch {
                    val result = AuthAdapter().login(username, password)

                    if (result.isSuccess) {
                        // navigate
                        println("sucesso")
                    } else {
                        errorMessage = result.errorMessage ?: ""
                        showError = true
                    }
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
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(top = Dimensions.SIZE_80.dp())
                    .align(Alignment.TopCenter)
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    text = stringResource(Res.string.log_into_account),
                    fontSize = Dimensions.SIZE_32.sp(),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                OutlinedTextField(
                    value = username,
                    onValueChange = {
                        username = it
                        showError = false
                    },
                    label = { Text(stringResource(Res.string.username)) },
                    modifier = Modifier.padding(top = Dimensions.SIZE_16.dp())
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        showError = false
                    },
                    label = { Text(stringResource(Res.string.password)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = passwordVisualTransformation,
                    trailingIcon = {
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(passwordToggleImage, passwordToggleDescription)
                        }
                    },
                )

                AnimatedVisibility(
                    visible = showError,
                    enter = fadeIn(initialAlpha = 0.4f),
                    exit = fadeOut(animationSpec = tween(durationMillis = 200)),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = errorMessage,
                        fontSize = Dimensions.SIZE_12.sp(),
                        color = Color.Red,
                        modifier = Modifier
                            .padding(top = Dimensions.SIZE_16.dp())
                            .align(Alignment.CenterHorizontally),
                    )
                }

                Button(
                    onClick = {
                        tryLogin()
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = Dimensions.SIZE_16.dp())
                ) {
                    Text(stringResource(Res.string.`continue`))
                }

                Text(
                    text = stringResource(Res.string.problems_to_log_in),
                    fontSize = Dimensions.SIZE_16.sp(),
                    modifier = Modifier
                        .padding(top = Dimensions.SIZE_16.dp())
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = stringResource(Res.string.open_a_ticket),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.Companion
                        .align(Alignment.CenterHorizontally)
                )

            }
            Text(
                text = "v${getPlatform().version}",
                fontSize = Dimensions.SIZE_12.sp(),
                modifier = Modifier.align(Alignment.TopStart)
            )
        }

    }

}
