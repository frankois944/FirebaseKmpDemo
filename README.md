# Setup Firebase for Compose multiplatform Android, iOS, desktop and web (almost).

This repository is a sample showing how to load Firebase and use some features on a Compose multiplaform Application.

Based on the [project Firebase for Kotlin](https://github.com/GitLiveApp/firebase-kotlin-sdk).


> [!NOTE]  
> [Firebase for Kotlin](https://github.com/GitLiveApp/firebase-kotlin-sdk) is totally optional for iOS; you can directly use the Firebase library in Kotlin if you exported them to Kotlin.
>
>  Set [exportToKotlin = true](https://github.com/frankois944/FirebaseKmpDemo/blob/505fd0bf45b131621049d4f3489f150721e74bdd/composeApp/build.gradle.kts#L199C17-L204C19) and remove the GitLiveApp dependencies.


It's implementing and loading all modules created by this project, except crashlytics (use [CrashKiOS](https://github.com/touchlab/CrashKiOS) instead).

I'm using [spm4kmp](https://github.com/frankois944/spm4Kmp) for compiling Firebase iOS

## Setup

Each targeted platform has its own way to set up

### iOS

For starting up the SDK, the iOS app is following the [official way](https://firebase.google.com/docs/ios/setup) and the CocoaPods integration.

Some Swift code needs [to be written](https://github.com/frankois944/FirebaseKmpDemo/blob/main/iosApp/iosApp/iOSApp.swift) to set up the environment.

An iOS Firebase app needs to be created and added to the iOS project as recommended.

### Android

For starting up the SDK, the Android app is following the [official way](https://firebase.google.com/docs/android/setup) and the Gradle integration.

No code needs to be written, as the Gradle plugin does the job.

An Android Firebase app needs to be created and added to the Android project as recommended.

### Desktop

To start up the SDK, the desktop app needs to be manually loaded.

Some Kotlin code [needs to be written](https://github.com/frankois944/FirebaseKmpDemo/blob/main/composeApp/src/desktopMain/kotlin/fr/francoisdabonot/firebasekmpdemo/main.kt).

An Android Firebase app needs to be created; even if it's not one, it's still compatible; maybe a Firebase web app configuration should also do the job.

### Web with KotlinJS

To start up the SDK, the WebApp app needs to be manually loaded.

Some Kotlin code [needs to be written](https://github.com/frankois944/FirebaseKmpDemo/blob/main/webApp/src/jsMain/kotlin/com/example/App.kt).

The firebase needs to be added as a npm dependency, currently `implementation(npm("firebase", "10.12.2"))`, or a ealier version.

> [!WARNING]  
> firebase-kotlin-sdk for JS is out of date, some kotlin methods won't work as the JS API changed
> 
> I recommend to complete the missing method yourself.
