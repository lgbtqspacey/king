package com.lgbtqspacey.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.lgbtqspacey.admin.features.auth.Login
import com.lgbtqspacey.admin.features.dashboard.DashBoard

@Composable
fun App() {
    val coroutineScope = rememberCoroutineScope()
    var isLoggedIn by remember { mutableStateOf(false) }

    if (isLoggedIn) {
        DashBoard()
    } else {
        Login()
    }

}

