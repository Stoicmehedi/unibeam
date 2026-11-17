package com.unibeam.core.model

/** A TV the user has discovered or saved. */
data class TvDevice(
    val id: String,
    val name: String,
    val platform: TvPlatform,
    /** IP address for Wi-Fi control, or null if the TV is only reachable via Bluetooth. */
    val host: String? = null,
    /** Bluetooth MAC address for HID control, or null if not paired. */
    val bluetoothAddress: String? = null,
) {
    val supportsWifi: Boolean get() = host != null
    val supportsBluetooth: Boolean get() = bluetoothAddress != null
}

enum class TvPlatform(val displayName: String) {
    ANDROID_TV("Android TV / Google TV"),
    SAMSUNG_TIZEN("Samsung (Tizen)"),
    LG_WEBOS("LG (webOS)"),
    ROKU("Roku"),
    FIRE_TV("Fire TV"),
    GENERIC_BLUETOOTH("Bluetooth TV"),
}
