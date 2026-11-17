plugins {
    id("unibeam.jvm.library")
}

dependencies {
    api(projects.core.model)
    api(libs.kotlinx.coroutines.core)
}
