package com.unibeam.core.model

sealed interface ConnectionState {
    data object Disconnected : ConnectionState
    data object Connecting : ConnectionState

    /** The TV is showing a PIN/approval prompt that the user must complete. */
    data class AwaitingPairing(val requiresPin: Boolean) : ConnectionState

    data class Connected(val transport: Transport) : ConnectionState
    data class Failed(val reason: String, val cause: Throwable? = null) : ConnectionState
}

enum class Transport { WIFI, BLUETOOTH }

/** Optional features a controller may support; the UI only shows what's available. */
enum class Capability {
    KEYS,
    TEXT_INPUT,
    APP_LIST,
    APP_LAUNCH,
    TOUCHPAD,
    POWER_ON,
    VOLUME,
}
