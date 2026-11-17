# UniBeam

**A free, open-source universal TV remote for Android — over Wi-Fi and Bluetooth.**

UniBeam lets you control your smart TV from your phone. It talks to TVs over your
local Wi-Fi network using each platform's own protocol, and falls back to acting as a
Bluetooth keyboard/remote for TVs that can't be reached over Wi-Fi.

> 🚧 Early development. See [PLAN.md](PLAN.md) for the roadmap.

## Supported TVs (planned)

| Platform | Wi-Fi | Bluetooth |
|---|---|---|
| Android TV / Google TV (Xiaomi, Sony, TCL, Chromecast…) | ✅ | ✅ |
| Samsung (Tizen) | ✅ | Partial |
| LG (webOS) | ✅ | Partial |
| Roku | ✅ | — |
| Fire TV | — | ✅ |
| Other Bluetooth TVs | — | ✅ (generic) |

## Principles

- **No ads, no tracking.** Crash reports are opt-in only.
- **No Google Play Services dependency**, so UniBeam can ship on F-Droid.
- **Local only.** Your phone talks directly to your TV; nothing goes through a server.

## Building

Requirements: JDK 17 and the Android SDK (API 35). Android Studio Ladybug or newer is recommended.

```bash
./gradlew :app:assembleDebug        # build the debug APK
./gradlew testDebugUnitTest         # run unit tests
./gradlew detekt                    # static analysis + formatting checks
```

Minimum Android version: **9.0 (API 28)**, required for the Bluetooth HID profile.

## Project structure

```
app/                 Application, navigation shell
build-logic/         Gradle convention plugins (shared module config)
core/model           Pure Kotlin domain models
core/common          Coroutine dispatchers, app scope
core/data            Settings (DataStore) and, later, saved TVs (Room)
core/ui              Theme, icons, shared Compose components
protocol/api         TvController — the interface every protocol implements
feature/remote       Remote control screen
feature/devices      TV discovery & management
feature/settings     Settings & about
```

## Contributing

Contributions are welcome! Please read [CONTRIBUTING.md](CONTRIBUTING.md).

## License

UniBeam is licensed under the [Apache License 2.0](LICENSE).

TV brand names are trademarks of their respective owners. UniBeam is not affiliated with
or endorsed by any TV manufacturer.
