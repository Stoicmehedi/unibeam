package com.unibeam.core.model

data class UserSettings(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val dynamicColor: Boolean = true,
    val hapticFeedback: Boolean = true,
)

enum class ThemeMode { SYSTEM, LIGHT, DARK }
