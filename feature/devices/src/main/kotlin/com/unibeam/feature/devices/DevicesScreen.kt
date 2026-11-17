package com.unibeam.feature.devices

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.unibeam.core.ui.component.EmptyState
import com.unibeam.core.ui.icon.UniBeamIcons
import com.unibeam.core.ui.theme.UniBeamTheme

/** Saved and discovered TVs. M0: empty state; scanning arrives in M2 (discovery). */
@Composable
fun DevicesScreen(modifier: Modifier = Modifier) {
    EmptyState(
        icon = UniBeamIcons.Search,
        title = stringResource(R.string.devices_empty_title),
        message = stringResource(R.string.devices_empty_message),
        actionLabel = stringResource(R.string.devices_scan),
        actionEnabled = false, // enabled once discovery is implemented (M2)
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun DevicesScreenPreview() {
    UniBeamTheme(dynamicColor = false) {
        DevicesScreen()
    }
}
