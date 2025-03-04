package com.lgbtqspacey.king.helpers

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Dimensions system
 * @property dp get value as dp. General use.
 * @property sp get value as sp. For fonts.
 */
@Suppress("Unused")
enum class Dimensions(private val value: Int) {
    SIZE_1(1),
    SIZE_4(4),
    SIZE_8(8),
    SIZE_12(12),
    SIZE_16(16),
    SIZE_20(20),
    SIZE_24(24),
    SIZE_28(28),
    SIZE_32(32),
    SIZE_36(36),
    SIZE_40(40),
    SIZE_48(48),
    SIZE_56(56),
    SIZE_60(60),
    SIZE_64(64),
    SIZE_72(72),
    SIZE_80(80),
    SIZE_96(96),
    SIZE_104(104),
    SIZE_120(120),
    SIZE_144(144),
    SIZE_160(160),
    SIZE_192(192),
    SIZE_224(224),
    SIZE_240(240);

    fun dp(): Dp = value.dp
    fun sp(): TextUnit = value.sp
}
