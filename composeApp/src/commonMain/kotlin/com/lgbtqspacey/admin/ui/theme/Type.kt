package com.lgbtqspacey.admin.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.lgbtqspacey.admin.commonMain.composeResources.Nunito
import com.lgbtqspacey.admin.commonMain.composeResources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun getFont(): FontFamily {
    return FontFamily(Font(Res.font.Nunito))
}

@Composable
fun getTypography(): Typography {
    return Typography(
        bodyMedium = TextStyle(
            fontFamily = getFont(),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        titleLarge = TextStyle(
            fontFamily = getFont(),
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        labelSmall = TextStyle(
            fontFamily = getFont(),
            fontWeight = FontWeight.Normal,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        ),
        headlineLarge = TextStyle(
            fontFamily = getFont(),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 32.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        )
    )
}
