# Contributing to UniBeam

Thanks for helping make UniBeam better!

## Reporting bugs

Open an issue and include:
- Phone model and Android version
- TV brand, model and OS version
- Connection type (Wi-Fi or Bluetooth)
- Steps to reproduce, and what you expected

## Pull requests

1. Fork the repo and create a branch from `main`.
2. Keep changes focused — one feature or fix per PR.
3. Make sure these pass locally:
   ```bash
   ./gradlew detekt testDebugUnitTest :app:assembleDebug
   ```
4. Add tests for new logic (ViewModels, protocol encoders/decoders).
5. Describe what you changed and how you tested it, ideally on a real TV.

## Code style

- Kotlin official style, enforced by detekt + ktlint rules (`./gradlew detekt`).
- UI in Jetpack Compose; state exposed from ViewModels as `StateFlow`.
- New TV protocols go in their own `protocol/<name>` module and implement
  `TvController` from `protocol/api`.
- No proprietary dependencies (Firebase, Google Play Services) — UniBeam must stay F-Droid compatible.

## Adding support for a new TV

If you own a TV that UniBeam doesn't support yet, protocol captures and documentation
links are hugely helpful even without code. Open an issue with the `new-tv` label.

By contributing you agree that your contributions are licensed under the Apache License 2.0.
