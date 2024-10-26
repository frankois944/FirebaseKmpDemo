# Setup Firebase for Kotlin mutliplaftorm for Android, iOS and Desktop.

This repository is a sample showing how to load Firebase and some features.

Based on the project [Firebase for Kotlin](https://github.com/GitLiveApp/firebase-kotlin-sdk).

It's implementing and loading all modules created by this project, except crashlytics (please use [CrashKiOS](https://github.com/touchlab/CrashKiOS)).

It's **really slow** to load because of Cocoapods is needed by the iOS app, could be reduced by the modules you really need.

## Setup

Each targeted platform has its own way to setup

### iOS

For starting up the sdk, the iOS app is following the [official](https://firebase.google.com/docs/ios/setup) and the cocoapods integration.

Some swift code [need to be written](https://github.com/frankois944/FirebaseKmpDemo/blob/main/iosApp/iosApp/iOSApp.swift) for setting up the environnement. 

An **iOS firebase app** need to be created and added to the iOS project as recommended.

### Android

For starting up the sdk, the iOS app is following the [official](https://firebase.google.com/docs/android/setup) and the gradle integration.

No code need to be written as the gradle plugin do the job

An **Android firebase app** need to be created and added to the Android project as recommended.

### Desktop

For starting up the sdk, the desktop app need to be manually loaded.

Some kotlin code [need to be written](https://github.com/frankois944/FirebaseKmpDemo/blob/main/composeApp/src/desktopMain/kotlin/fr/francoisdabonot/firebasekmpdemo/main.kt)

An **Android firebase app** need to be created, even it's not one, it's compatible.

