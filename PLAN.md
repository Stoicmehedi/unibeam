# UniBeam — Universal TV Remote — Project Plan (Bluetooth + Wi-Fi)

- **App name:** UniBeam
- **Package ID:** `com.unibeam.remote`

## 1. Product scope

### v1.0 features
- Auto-discover TVs on Wi-Fi + pair over Bluetooth
- Save multiple TVs, quick switch between them
- Remote screen: D-pad, OK, Back, Home, volume, channel, mute, power, input
- Touchpad mode (swipe navigation) and number pad
- Keyboard: type directly into TV search fields
- App launcher: list and open installed TV apps (Wi-Fi only)
- Haptic feedback, dark/light theme, Material You colours
- Wake-on-LAN power-on where supported
- Home-screen widget + Quick Settings tile
- Phone hardware volume buttons control TV volume

### Later (v1.x)
- Voice search via phone microphone
- Air-mouse (gyroscope)
- Wear OS companion
- Macros (e.g. "Netflix mode")

## 2. Supported TVs and protocols

| TV / platform | Wi-Fi protocol | Bluetooth |
|---|---|---|
| Android TV / Google TV | Android TV Remote v2 (TLS + protobuf, pairing code) | HID ✔ |
| Samsung Tizen (2016+) | WebSocket, ports 8001/8002 (token) | HID (partial) |
| LG webOS | SSAP WebSocket, port 3000/3001 (pairing prompt) | HID (partial) |
| Roku | ECP over HTTP, port 8060 | ✘ |
| Fire TV | ADB over Wi-Fi (optional), else Bluetooth | HID ✔ |
| Any other Bluetooth TV | — | Generic HID keyboard + media keys |

Discovery:
- mDNS/NSD (`_androidtvremote2._tcp`, `_googlecast._tcp`)
- SSDP/UPnP for Samsung, LG, Roku

## 3. Architecture

Clean Architecture + MVVM, multi-module:

```
app/                    → navigation, DI setup, theme
core/
  model/                → TvDevice, RemoteKey, ConnectionState
  common/               → coroutines, Result, logging
  data/                 → Room DB (saved TVs), DataStore (settings)
  ui/                   → design system, shared Compose components
protocol/
  api/                  → TvController interface
  bluetooth-hid/        → BluetoothHidDevice implementation
  androidtv/            → Android TV Remote v2
  samsung/
  lg/
  roku/
discovery/              → NSD + SSDP scanner, Bluetooth scanner
feature/
  onboarding/
  devices/              → scan, pair, manage TVs
  remote/               → main remote screen
  apps/                 → app launcher
  settings/
widget/                 → Glance widget + QS tile
```

Core abstraction:

```kotlin
interface TvController {
    val state: StateFlow<ConnectionState>
    val capabilities: Set<Capability>   // APPS, TEXT, POWER_ON, TOUCHPAD…
    suspend fun connect()
    suspend fun disconnect()
    suspend fun sendKey(key: RemoteKey)
    suspend fun sendText(text: String)
}
```

- UI shows only the buttons the connected TV supports (capability-driven).
- Auto-fallback: if Wi-Fi drops, switch to Bluetooth HID for the same TV.

## 4. Tech stack
- Kotlin 2.x, Jetpack Compose + Material 3, Coroutines/Flow
- Hilt (DI), Room + DataStore (storage)
- OkHttp (WebSocket/HTTP), protobuf-lite/Wire (Android TV)
- Bouncy Castle (Android TV TLS certificates)
- Glance (widget), foreground service (keep HID connection alive)
- Tests: JUnit, MockK, Turbine, Compose UI tests, fake TV servers
- Gradle version catalogs, Detekt + ktlint, GitHub Actions CI
- minSdk 28, targetSdk 35

## 5. Milestones

| # | Milestone | Deliverable |
|---|---|---|
| M0 ✅ | Project setup | Modules, CI, theme, navigation shell |
| M1 | Bluetooth HID | Register HID, pair, D-pad/media keys, keyboard — tested on real TV |
| M2 | Discovery | NSD + SSDP scan, device list UI |
| M3 | Android TV protocol | Pairing-code flow, keys, text, app launch |
| M4 | Samsung + LG | Token/prompt pairing, keys, app list |
| M5 | Roku + Wake-on-LAN | ECP control, power-on |
| M6 | Remote UX polish | Touchpad, number pad, haptics, volume keys, auto-fallback |
| M7 | Extras | Widget, QS tile, settings, onboarding |
| M8 | Hardening | Error states, reconnect, crash reporting, accessibility, multi-device testing |
| M9 | Release | Privacy policy, Play Store listing, signing, AAB |

## 6. Risks
- **HID unsupported on some phones** → detect at startup, fall back to Wi-Fi only.
- **TV makers change protocols** → each protocol isolated in its own module.
- **Real TVs needed for testing** → at least one Android TV/Chromecast, ideally Samsung or LG too.
- **Play Store policy** → declare Bluetooth/nearby-devices permissions; no brand logos in listing.

## 7. Open decisions (needed before M0)
1. ~~App name and package ID~~ → UniBeam, `com.unibeam.remote`
2. ~~TVs available for testing~~ → Xiaomi Android TV (primary test device)
3. ~~Monetization~~ → Fully open source, free, no ads

## 8. Open-source requirements
- License: Apache 2.0 (proposed; alternative GPLv3 to force forks to stay open)
- Public GitHub repo: README, CONTRIBUTING, CODE_OF_CONDUCT, issue/PR templates
- No proprietary dependencies (no Firebase/Google Play Services) → F-Droid compatible
- Crash reporting: ACRA (opt-in, user-controlled) instead of Crashlytics
- Distribution: GitHub Releases + F-Droid + Google Play
- Reproducible builds; signing keys kept out of the repo
