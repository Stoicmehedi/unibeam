package com.unibeam.feature.settings

import app.cash.turbine.ReceiveTurbine
import app.cash.turbine.test
import com.unibeam.core.data.settings.UserSettingsRepository
import com.unibeam.core.model.ThemeMode
import com.unibeam.core.model.UserSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SettingsViewModelTest {

    private val repository = FakeUserSettingsRepository()

    @Before
    fun setUp() = Dispatchers.setMain(UnconfinedTestDispatcher())

    @After
    fun tearDown() = Dispatchers.resetMain()

    @Test
    fun `emits stored settings`() = runTest {
        val viewModel = SettingsViewModel(repository)
        viewModel.uiState.test {
            assertEquals(SettingsUiState.Ready(UserSettings()), awaitReady())
        }
    }

    @Test
    fun `changing theme mode updates state`() = runTest {
        val viewModel = SettingsViewModel(repository)
        viewModel.uiState.test {
            awaitReady()
            viewModel.setThemeMode(ThemeMode.DARK)
            assertEquals(ThemeMode.DARK, awaitReady().settings.themeMode)
        }
    }

    /** Skips the initial Loading state, whose presence depends on subscription timing. */
    private suspend fun ReceiveTurbine<SettingsUiState>.awaitReady(): SettingsUiState.Ready {
        while (true) {
            val item = awaitItem()
            if (item is SettingsUiState.Ready) return item
        }
    }
}

private class FakeUserSettingsRepository : UserSettingsRepository {
    override val settings = MutableStateFlow(UserSettings())

    override suspend fun setThemeMode(mode: ThemeMode) = settings.update { it.copy(themeMode = mode) }

    override suspend fun setDynamicColor(enabled: Boolean) = settings.update { it.copy(dynamicColor = enabled) }

    override suspend fun setHapticFeedback(enabled: Boolean) = settings.update { it.copy(hapticFeedback = enabled) }
}
