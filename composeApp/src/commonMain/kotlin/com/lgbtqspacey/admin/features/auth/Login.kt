package com.lgbtqspacey.admin.features.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.lgbtqspacey.admin.backend.adapter.AuthAdapter
import com.lgbtqspacey.admin.commonMain.composeResources.Res
import com.lgbtqspacey.admin.commonMain.composeResources.`continue`
import com.lgbtqspacey.admin.commonMain.composeResources.fill_all_fields
import com.lgbtqspacey.admin.commonMain.composeResources.hide_password
import com.lgbtqspacey.admin.commonMain.composeResources.ic_dark_mode
import com.lgbtqspacey.admin.commonMain.composeResources.ic_light_mode
import com.lgbtqspacey.admin.commonMain.composeResources.ic_visibility
import com.lgbtqspacey.admin.commonMain.composeResources.ic_visibility_off
import com.lgbtqspacey.admin.commonMain.composeResources.log_into_account
import com.lgbtqspacey.admin.commonMain.composeResources.open_a_ticket
import com.lgbtqspacey.admin.commonMain.composeResources.password
import com.lgbtqspacey.admin.commonMain.composeResources.problems_to_log_in
import com.lgbtqspacey.admin.commonMain.composeResources.show_password
import com.lgbtqspacey.admin.commonMain.composeResources.toggle_dark_mode
import com.lgbtqspacey.admin.commonMain.composeResources.toggle_light_mode
import com.lgbtqspacey.admin.commonMain.composeResources.username
import com.lgbtqspacey.admin.getPlatform
import com.lgbtqspacey.admin.helpers.Dimensions
import com.lgbtqspacey.admin.helpers.Screens
import com.lgbtqspacey.admin.ui.theme.AppTheme
import kotlinx.coroutines.launch
import moe.tlaster.precompose.navigation.Navigator
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun Login(navigator: Navigator) {
    val coroutineScope = rememberCoroutineScope()

    var password by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var errorCode by remember { mutableStateOf("") }

    val passwordVisualTransformation: VisualTransformation?
    val passwordToggleImage: ImageVector?
    val passwordToggleDescription: String?

    /**
     * Call login method from backend
     * @see AuthAdapter
     */
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
                    navigator.navigate(Screens.DASHBOARD)
                } else {
                    errorMessage = result.errorMessage
                    errorCode = "Código do erro: ${result.errorCode}"
                    showError = true
                }
            }
        }
    }

    /**
     * Toggle password visibility
     */
    if (isPasswordVisible) {
        passwordVisualTransformation = VisualTransformation.None
        passwordToggleImage = vectorResource(Res.drawable.ic_visibility_off)
        passwordToggleDescription = stringResource(Res.string.hide_password)
    } else {
        passwordVisualTransformation = PasswordVisualTransformation()
        passwordToggleImage = vectorResource(Res.drawable.ic_visibility)
        passwordToggleDescription = stringResource(Res.string.show_password)
    }

    /**
     * Screen container
     */
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Column(
            modifier = Modifier
                .padding(top = Dimensions.SIZE_80.dp())
                .align(Alignment.TopCenter)
                .align(Alignment.BottomCenter)
        ) {
            /**
             * Login form
             */
            Text(
                text = stringResource(Res.string.log_into_account),
                fontSize = Dimensions.SIZE_32.sp(),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )

            OutlinedTextField(
                value = username,
                onValueChange = {
                    username = it
                    showError = false
                },
                singleLine = true,
                label = { Text(stringResource(Res.string.username)) },
                modifier = Modifier.padding(top = Dimensions.SIZE_16.dp()),
                colors = TextFieldDefaults.colors(MaterialTheme.colorScheme.primary)
            )

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    showError = false
                },
                singleLine = true,
                label = { Text(stringResource(Res.string.password)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = passwordVisualTransformation,
                colors = TextFieldDefaults.colors(MaterialTheme.colorScheme.primary),
                trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(passwordToggleImage, passwordToggleDescription)
                    }
                },
            )

            /**
             * Container to display errors
             */
            AnimatedVisibility(
                visible = showError,
                enter = fadeIn(initialAlpha = 0.4f),
                exit = fadeOut(animationSpec = tween(durationMillis = 200)),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = Dimensions.SIZE_8.dp())
            ) {
                Column {
                    Text(
                        text = errorMessage,
                        fontSize = Dimensions.SIZE_12.sp(),
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                    )
                    Text(
                        text = errorCode,
                        fontSize = Dimensions.SIZE_12.sp(),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                    )
                }
            }

            /**
             * Login button
             */
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

            /**
             * Foot notes
             */
            Text(
                text = stringResource(Res.string.problems_to_log_in),
                fontSize = Dimensions.SIZE_16.sp(),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(top = Dimensions.SIZE_16.dp())
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(Res.string.open_a_ticket),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.Companion
                    .align(Alignment.CenterHorizontally)
            )

        }

        /**
         * App version
         */
        Text(
            text = "v${getPlatform().version}",
            fontSize = Dimensions.SIZE_12.sp(),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
