package com.lgbtqspacey.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.lgbtqspacey.admin.database.Database
import com.lgbtqspacey.admin.features.auth.Login
import com.lgbtqspacey.admin.features.dashboard.Home
import com.lgbtqspacey.admin.features.dashboard.Settings
import com.lgbtqspacey.admin.helpers.Screens
import com.lgbtqspacey.admin.ui.theme.AppTheme
import kotlinx.coroutines.launch
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
fun App() {
    val coroutineScope = rememberCoroutineScope()
    var isLoggedIn by remember { mutableStateOf(false) }
    var loaded by remember { mutableStateOf(false) }
    val isDarkMode = remember { mutableStateOf(false) }

    coroutineScope.launch {
        val session = Database().session.getSession()
        isDarkMode.value = Database().settings.getSettings().isDarkMode
        isLoggedIn = session.token.isNotEmpty()
        loaded = true
    }

    PreComposeApp {
        val navigator = rememberNavigator()
        AppTheme(isDarkMode.value) {
            NavHost(
                navigator = navigator,
                initialRoute = Screens.LOADING,
            ) {
                scene("/app") {
                    App()
                }

                scene(Screens.LOADING) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    }

                    if (loaded) {
//                        if (isLoggedIn) {
//                            navigator.navigate(Screens.HOME)
//                        } else {
//                            navigator.navigate(Screens.LOGIN)
//                        }
                        navigator.navigate(Screens.HOME)
                    }
                }

                scene(Screens.LOGIN) {
                    Login(navigator)
                }

                scene(Screens.HOME) {
                    Home(navigator)
                }

                scene(Screens.SETTINGS) {
                    Settings(navigator)
                }

                scene(Screens.USERS) {
                    // todo
                }

                scene(Screens.REPORTS) {
                    // todo
                }

                scene(Screens.ROLES) {
                    // todo
                }

                scene(Screens.PROFILE) {
                    // todo
                }
            }
        }
    }
}
