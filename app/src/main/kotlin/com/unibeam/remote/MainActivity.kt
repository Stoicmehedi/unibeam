package com.unibeam.remote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.unibeam.core.model.UserSettings
import com.unibeam.core.ui.theme.UniBeamTheme
import com.unibeam.remote.ui.UniBeamApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val loaded by viewModel.settings.collectAsStateWithLifecycle()
            val settings = loaded ?: UserSettings()
            UniBeamTheme(
                themeMode = settings.themeMode,
                dynamicColor = settings.dynamicColor,
            ) {
                UniBeamApp(appVersion = BuildConfig.VERSION_NAME)
            }
        }
    }
}
