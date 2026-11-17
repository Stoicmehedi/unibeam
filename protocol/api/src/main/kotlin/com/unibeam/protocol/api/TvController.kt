package com.unibeam.protocol.api

import com.unibeam.core.model.Capability
import com.unibeam.core.model.ConnectionState
import com.unibeam.core.model.RemoteKey
import com.unibeam.core.model.TvDevice
import kotlinx.coroutines.flow.StateFlow

/**
 * Common contract implemented by every transport/protocol
 * (Bluetooth HID, Android TV Remote v2, Samsung, LG, Roku…).
 */
interface TvController {
    val device: TvDevice
    val state: StateFlow<ConnectionState>
    val capabilities: Set<Capability>

    suspend fun connect()
    suspend fun disconnect()

    /** Completes a PIN-based pairing flow (only when [state] is AwaitingPairing with a PIN). */
    suspend fun submitPairingPin(pin: String) {
        throw UnsupportedOperationException("${device.platform} does not use PIN pairing")
    }

    suspend fun sendKey(key: RemoteKey)

    suspend fun sendText(text: String) {
        throw UnsupportedOperationException("Text input not supported")
    }

    suspend fun listApps(): List<TvApp> = emptyList()

    suspend fun launchApp(appId: String) {
        throw UnsupportedOperationException("App launch not supported")
    }
}

data class TvApp(val id: String, val name: String, val iconUrl: String? = null)

/** Creates controllers for a given device; each protocol module provides one. */
interface TvControllerFactory {
    fun supports(device: TvDevice): Boolean
    fun create(device: TvDevice): TvController
}
