package com.unibeam.core.ui.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

/** Central icon set so screens don't import Material icons directly. */
object UniBeamIcons {
    val Devices: ImageVector = Icons.AutoMirrored.Filled.List
    val Settings: ImageVector = Icons.Filled.Settings
    val Search: ImageVector = Icons.Filled.Search

    /** A handheld remote with a beam arc above it. */
    val Remote: ImageVector by lazy {
        materialIcon(name = "UniBeam.Remote") {
            // Remote body
            materialPath {
                moveTo(9f, 8f)
                horizontalLineToRelative(6f)
                arcToRelative(1.5f, 1.5f, 0f, false, true, 1.5f, 1.5f)
                verticalLineToRelative(11f)
                arcToRelative(1.5f, 1.5f, 0f, false, true, -1.5f, 1.5f)
                horizontalLineToRelative(-6f)
                arcToRelative(1.5f, 1.5f, 0f, false, true, -1.5f, -1.5f)
                verticalLineToRelative(-11f)
                arcToRelative(1.5f, 1.5f, 0f, false, true, 1.5f, -1.5f)
                close()
                // Button hole
                moveTo(12f, 10.5f)
                arcToRelative(1.5f, 1.5f, 0f, true, false, 0f, 3f)
                arcToRelative(1.5f, 1.5f, 0f, true, false, 0f, -3f)
                close()
            }
            // Beam arc
            materialPath {
                moveTo(7.05f, 5.05f)
                lineTo(8.46f, 6.46f)
                arcTo(5f, 5f, 0f, false, true, 15.54f, 6.46f)
                lineTo(16.95f, 5.05f)
                arcTo(7f, 7f, 0f, false, false, 7.05f, 5.05f)
                close()
            }
        }
    }
}
