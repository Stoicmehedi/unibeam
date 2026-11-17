package com.unibeam.feature.remote

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.unibeam.core.ui.component.EmptyState
import com.unibeam.core.ui.icon.UniBeamIcons
import com.unibeam.core.ui.theme.UniBeamTheme

/** Main remote control screen. M0: shows the "no TV connected" state only. */
@Composable
fun RemoteScreen(
    onFindTvClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    EmptyState(
        icon = UniBeamIcons.Remote,
        title = stringResource(R.string.remote_empty_title),
        message = stringResource(R.string.remote_empty_message),
        actionLabel = stringResource(R.string.remote_empty_action),
        onAction = onFindTvClick,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun RemoteScreenPreview() {
    UniBeamTheme(dynamicColor = false) {
        RemoteScreen(onFindTvClick = {})
    }
}
