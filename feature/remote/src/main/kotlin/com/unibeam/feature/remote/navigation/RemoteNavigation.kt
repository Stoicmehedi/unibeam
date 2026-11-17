package com.unibeam.feature.remote.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.unibeam.feature.remote.RemoteScreen
import kotlinx.serialization.Serializable

@Serializable
data object RemoteRoute

fun NavController.navigateToRemote(navOptions: NavOptions? = null) = navigate(RemoteRoute, navOptions)

fun NavGraphBuilder.remoteScreen(onFindTvClick: () -> Unit) {
    composable<RemoteRoute> {
        RemoteScreen(onFindTvClick = onFindTvClick)
    }
}
