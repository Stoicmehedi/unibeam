plugins {
    id("unibeam.android.feature")
}

android {
    namespace = "com.unibeam.feature.settings"
}

dependencies {
    implementation(projects.core.data)
}
