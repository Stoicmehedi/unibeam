plugins {
    id("unibeam.android.library")
    id("unibeam.android.compose")
}

android {
    namespace = "com.unibeam.core.ui"
}

dependencies {
    api(projects.core.model)
    api(libs.androidx.compose.material.icons.core)
}
