package com.unibeam.feature.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.unibeam.feature.settings.SettingsScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data object SettingsRoute

fun NavController.navigateToSettings(navOptions: NavOptions? = null) = navigate(SettingsRoute, navOptions)

fun NavGraphBuilder.settingsScreen(appVersion: String) {
    composable<SettingsRoute> {
        SettingsScreenRoute(appVersion = appVersion)
    }
}
