package com.unibeam.core.data.settings

import com.unibeam.core.model.ThemeMode
import com.unibeam.core.model.UserSettings
import kotlinx.coroutines.flow.Flow

interface UserSettingsRepository {
    val settings: Flow<UserSettings>

    suspend fun setThemeMode(mode: ThemeMode)
    suspend fun setDynamicColor(enabled: Boolean)
    suspend fun setHapticFeedback(enabled: Boolean)
}
