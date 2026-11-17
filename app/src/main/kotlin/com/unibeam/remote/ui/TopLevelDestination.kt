package com.unibeam.remote.ui

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.unibeam.core.ui.icon.UniBeamIcons
import com.unibeam.feature.devices.navigation.DevicesRoute
import com.unibeam.feature.remote.navigation.RemoteRoute
import com.unibeam.feature.settings.navigation.SettingsRoute
import com.unibeam.remote.R

/** Bottom-navigation tabs. */
enum class TopLevelDestination(
    val route: Any,
    val icon: ImageVector,
    @StringRes val titleRes: Int,
) {
    REMOTE(RemoteRoute, UniBeamIcons.Remote, R.string.tab_remote),
    DEVICES(DevicesRoute, UniBeamIcons.Devices, R.string.tab_devices),
    SETTINGS(SettingsRoute, UniBeamIcons.Settings, R.string.tab_settings),
}
