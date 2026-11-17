plugins {
    id("unibeam.android.library")
    id("unibeam.android.hilt")
}

android {
    namespace = "com.unibeam.core.common"
}

dependencies {
    api(libs.kotlinx.coroutines.android)
}
