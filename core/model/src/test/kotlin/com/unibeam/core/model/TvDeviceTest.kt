package com.unibeam.core.model

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TvDeviceTest {

    @Test
    fun `device with host and bluetooth address supports both transports`() {
        val tv = TvDevice(
            id = "1",
            name = "Living room",
            platform = TvPlatform.ANDROID_TV,
            host = "192.168.1.20",
            bluetoothAddress = "AA:BB:CC:DD:EE:FF",
        )
        assertTrue(tv.supportsWifi)
        assertTrue(tv.supportsBluetooth)
    }

    @Test
    fun `bluetooth-only device does not support wifi`() {
        val tv = TvDevice(
            id = "2",
            name = "Bedroom",
            platform = TvPlatform.GENERIC_BLUETOOTH,
            bluetoothAddress = "AA:BB:CC:DD:EE:00",
        )
        assertFalse(tv.supportsWifi)
        assertTrue(tv.supportsBluetooth)
    }
}
