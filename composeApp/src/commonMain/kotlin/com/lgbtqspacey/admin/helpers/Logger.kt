package com.lgbtqspacey.admin.helpers

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Logger {
    private val now = LocalDateTime.now()
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    private val timestamp = now.format(formatter)

    fun info(msg: String) {
        println("$timestamp :: [INFO] :: => $msg")
    }

    fun error(msg: String, error: String) {
        println("$timestamp :: [ERROR] :: $msg => $error")
    }
}
