package com.unibeam.feature.settings

import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.unibeam.core.model.ThemeMode
import com.unibeam.core.model.UserSettings
import com.unibeam.core.ui.theme.UniBeamTheme

@Composable
internal fun SettingsScreenRoute(
    appVersion: String,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    SettingsScreen(
        uiState = uiState,
        appVersion = appVersion,
        onThemeModeChange = { viewModel.setThemeMode(it) },
        onDynamicColorChange = { viewModel.setDynamicColor(it) },
        onHapticFeedbackChange = { viewModel.setHapticFeedback(it) },
    )
}

@Composable
internal fun SettingsScreen(
    uiState: SettingsUiState,
    appVersion: String,
    onThemeModeChange: (ThemeMode) -> Unit,
    onDynamicColorChange: (Boolean) -> Unit,
    onHapticFeedbackChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        SettingsUiState.Loading -> Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        is SettingsUiState.Ready -> SettingsContent(
            settings = uiState.settings,
            appVersion = appVersion,
            onThemeModeChange = onThemeModeChange,
            onDynamicColorChange = onDynamicColorChange,
            onHapticFeedbackChange = onHapticFeedbackChange,
            modifier = modifier,
        )
    }
}

@Composable
private fun SettingsContent(
    settings: UserSettings,
    appVersion: String,
    onThemeModeChange: (ThemeMode) -> Unit,
    onDynamicColorChange: (Boolean) -> Unit,
    onHapticFeedbackChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val uriHandler = LocalUriHandler.current
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        SectionHeader(stringResource(R.string.settings_appearance))
        Column(Modifier.selectableGroup()) {
            ThemeMode.entries.forEach { mode ->
                ListItem(
                    headlineContent = { Text(stringResource(mode.labelRes)) },
                    leadingContent = { RadioButton(selected = settings.themeMode == mode, onClick = null) },
                    modifier = Modifier.selectable(
                        selected = settings.themeMode == mode,
                        role = Role.RadioButton,
                        onClick = { onThemeModeChange(mode) },
                    ),
                )
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            SwitchItem(
                title = stringResource(R.string.settings_dynamic_color),
                subtitle = stringResource(R.string.settings_dynamic_color_summary),
                checked = settings.dynamicColor,
                onCheckedChange = onDynamicColorChange,
            )
        }

        HorizontalDivider(Modifier.padding(vertical = 8.dp))
        SectionHeader(stringResource(R.string.settings_remote))
        SwitchItem(
            title = stringResource(R.string.settings_haptics),
            subtitle = stringResource(R.string.settings_haptics_summary),
            checked = settings.hapticFeedback,
            onCheckedChange = onHapticFeedbackChange,
        )

        HorizontalDivider(Modifier.padding(vertical = 8.dp))
        SectionHeader(stringResource(R.string.settings_about))
        ListItem(
            headlineContent = { Text(stringResource(R.string.settings_version)) },
            supportingContent = { Text(appVersion) },
        )
        ListItem(
            headlineContent = { Text(stringResource(R.string.settings_license)) },
            supportingContent = { Text(stringResource(R.string.settings_license_summary)) },
        )
        ListItem(
            headlineContent = { Text(stringResource(R.string.settings_source_code)) },
            supportingContent = { Text(SOURCE_URL) },
            modifier = Modifier.clickable { uriHandler.openUri(SOURCE_URL) },
        )
    }
}

@Composable
private fun SectionHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 4.dp),
    )
}

@Composable
private fun SwitchItem(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    ListItem(
        headlineContent = { Text(title) },
        supportingContent = { Text(subtitle) },
        trailingContent = { Switch(checked = checked, onCheckedChange = null) },
        modifier = Modifier.toggleable(
            value = checked,
            role = Role.Switch,
            onValueChange = onCheckedChange,
        ),
    )
}

private val ThemeMode.labelRes: Int
    get() = when (this) {
        ThemeMode.SYSTEM -> R.string.settings_theme_system
        ThemeMode.LIGHT -> R.string.settings_theme_light
        ThemeMode.DARK -> R.string.settings_theme_dark
    }

private const val SOURCE_URL = "https://github.com/Stoicmehedi/unibeam"

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    UniBeamTheme(dynamicColor = false) {
        SettingsScreen(
            uiState = SettingsUiState.Ready(UserSettings()),
            appVersion = "0.1.0",
            onThemeModeChange = {},
            onDynamicColorChange = {},
            onHapticFeedbackChange = {},
        )
    }
}
