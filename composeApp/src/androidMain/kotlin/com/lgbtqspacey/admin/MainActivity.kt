package com.lgbtqspacey.admin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.lgbtqspacey.admin.features.login.Login

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Login()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    Login()
}
