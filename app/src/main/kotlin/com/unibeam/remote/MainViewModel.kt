package com.unibeam.remote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unibeam.core.data.settings.UserSettingsRepository
import com.unibeam.core.model.UserSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    settingsRepository: UserSettingsRepository,
) : ViewModel() {

    /** Null until the stored settings load, so the UI doesn't flash the wrong theme. */
    val settings: StateFlow<UserSettings?> = settingsRepository.settings.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null,
    )
}
