plugins {
    id("unibeam.android.library")
    id("unibeam.android.hilt")
}

android {
    namespace = "com.unibeam.core.data"
}

dependencies {
    api(projects.core.model)
    implementation(projects.core.common)
    implementation(libs.androidx.datastore.preferences)
}
