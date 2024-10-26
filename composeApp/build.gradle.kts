import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.googleService)
}

// a list of pods using by Firebase
// first : the pod name
// second: add or not the -compiler-option -fmodules to the pod
val firebasePods = listOf(
    Pair("FirebaseCore", false),
    Pair("FirebaseAuth", true),
    /*Pair("FirebaseAnalytics", false),

    Pair("FirebaseFirestore", true),
    Pair("FirebaseDatabase", false),
    Pair("FirebaseFunctions", true),
    Pair("FirebaseMessaging", false),
    Pair("FirebaseInstallations", false),
    Pair("FirebaseRemoteConfig", false),
    Pair("FirebasePerformance", false),
    Pair("FirebaseStorage", true)*/
)

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    //iosX64()
    //iosArm64()
    iosSimulatorArm64()
    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            binaryOption("bundleId", "fr.francoisdabonot.firebasekmpdemo.composeapp")
            baseName = "ComposeApp"
            isStatic = true
        }
        firebasePods.forEach {
            pod(it.first) {
                version = libs.versions.firebaseiOSSDK.get()
                if (it.second) {
                    extraOpts += listOf("-compiler-option", "-fmodules")
                }
            }
        }
    }

    jvm("desktop")

    /*@OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
        binaries.executable()
    }*/

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(project.dependencies.platform("com.google.firebase:firebase-bom:33.5.1"))
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            if (firebasePods.firstOrNull { it.first.contains("Analytics")} != null) {
                implementation(libs.firebase.analytics)
            }
            if (firebasePods.firstOrNull { it.first.contains("Auth")} != null) {
                implementation(libs.firebase.auth)
            }
            if (firebasePods.firstOrNull { it.first.contains("Database")} != null) {
                implementation(libs.firebase.database)
            }
            if (firebasePods.firstOrNull { it.first.contains("Firestore")} != null) {
                implementation(libs.firebase.firestore)
            }
            if (firebasePods.firstOrNull { it.first.contains("Functions")} != null) {
                implementation(libs.firebase.functions)
            }
            if (firebasePods.firstOrNull { it.first.contains("Messaging")} != null) {
                implementation(libs.firebase.messaging)
            }
            if (firebasePods.firstOrNull { it.first.contains("Storage")} != null) {
                implementation(libs.firebase.storage)
            }
            if (firebasePods.firstOrNull { it.first.contains("Installations")} != null) {
                implementation(libs.firebase.installations)
            }
            if (firebasePods.firstOrNull { it.first.contains("Config")} != null) {
                implementation(libs.firebase.config)
            }
            if (firebasePods.firstOrNull { it.first.contains("Perf")} != null) {
                implementation(libs.firebase.perf)
            }
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            //   implementation(libs.firebase.java.sdk)
        }
    }
}

android {
    namespace = "fr.francoisdabonot.firebasekmpdemo"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "fr.francoisdabonot.firebasekmpdemo"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
    coreLibraryDesugaring(libs.desugar.jdk.libs)
}

compose.desktop {
    application {
        mainClass = "fr.francoisdabonot.firebasekmpdemo.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "fr.francoisdabonot.firebasekmpdemo.desktop"
            packageVersion = "1.0.0"
        }
    }
}
