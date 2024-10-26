# Setup Firebase for Compose multiplatform Android, iOS and desktop.

This repository is a sample showing how to load Firebase and some features.

Based on the [project Firebase for Kotlin](https://github.com/GitLiveApp/firebase-kotlin-sdk).

It's implementing and loading all modules created by this project, except crashlytics (please use [CrashKiOS](https://github.com/touchlab/CrashKiOS)).

It's really slow to load because of CocoaPods is needed by the iOS app, which could be reduced by the modules you really need.

## Setup

Each targeted platform has its own way to set up

### iOS

For starting up the SDK, the iOS app is following the [official](https://firebase.google.com/docs/ios/setup) and the CocoaPods integration.

Some Swift code needs [to be written](https://github.com/frankois944/FirebaseKmpDemo/blob/main/iosApp/iosApp/iOSApp.swift) to set up the environment.

An iOS Firebase app needs to be created and added to the iOS project as recommended.

### Android

For starting up the SDK, the iOS app is following the [official](https://firebase.google.com/docs/android/setup) and the Gradle integration.

No code needs to be written, as the Gradle plugin does the job.

An Android Firebase app needs to be created and added to the Android project as recommended.

### Desktop

To start up the SDK, the desktop app needs to be manually loaded.

Some Kotlin code [needs to be written](https://github.com/frankois944/FirebaseKmpDemo/blob/main/composeApp/src/desktopMain/kotlin/fr/francoisdabonot/firebasekmpdemo/main.kt).

An Android Firebase app needs to be created, even it's not one, it's still compatible.

