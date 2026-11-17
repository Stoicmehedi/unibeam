package com.unibeam.feature.devices.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.unibeam.feature.devices.DevicesScreen
import kotlinx.serialization.Serializable

@Serializable
data object DevicesRoute

fun NavController.navigateToDevices(navOptions: NavOptions? = null) = navigate(DevicesRoute, navOptions)

fun NavGraphBuilder.devicesScreen() {
    composable<DevicesRoute> {
        DevicesScreen()
    }
}
