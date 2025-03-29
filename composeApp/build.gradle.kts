import io.github.frankois944.spmForKmp.definition.product.ProductName
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.googleService)
    alias(libs.plugins.spmForKmp)
}

// a list of SPM package using by Firebase
val firebaseDeps =
    listOf(
        ProductName("FirebaseCore"),
        ProductName("FirebaseAnalytics"),
        ProductName("FirebaseAuth"),
        ProductName("FirebaseFirestore"),
        ProductName("FirebaseDatabase"),
        ProductName("FirebaseFunctions"),
        ProductName("FirebaseMessaging"),
        ProductName("FirebaseInstallations"),
        ProductName("FirebaseRemoteConfig"),
        ProductName("FirebasePerformance"),
        ProductName("FirebaseStorage"),
    )

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        // iosX64()
        // iosArm64()
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
        it.compilations {
            val main by getting {
                cinterops.create("nativeExample") // Create a CInterop for `nativeExample`
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
            if (firebaseDeps.firstOrNull { it.name.contains("Analytics") } != null) {
                implementation(libs.firebase.analytics)
            }
            if (firebaseDeps.firstOrNull { it.name.contains("Auth") } != null) {
                implementation(libs.firebase.auth)
            }
            if (firebaseDeps.firstOrNull { it.name.contains("Database") } != null) {
                implementation(libs.firebase.database)
            }
            if (firebaseDeps.firstOrNull { it.name.contains("Firestore") } != null) {
                implementation(libs.firebase.firestore)
            }
            if (firebaseDeps.firstOrNull { it.name.contains("Functions") } != null) {
                implementation(libs.firebase.functions)
            }
            if (firebaseDeps.firstOrNull { it.name.contains("Messaging") } != null) {
                implementation(libs.firebase.messaging)
            }
            if (firebaseDeps.firstOrNull { it.name.contains("Storage") } != null) {
                implementation(libs.firebase.storage)
            }
            if (firebaseDeps.firstOrNull { it.name.contains("Installations") } != null) {
                implementation(libs.firebase.installations)
            }
            if (firebaseDeps.firstOrNull { it.name.contains("Config") } != null) {
                implementation(libs.firebase.config)
            }
            if (firebaseDeps.firstOrNull { it.name.contains("Perf") } != null) {
                implementation(libs.firebase.perf)
            }
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}

android {
    namespace = "fr.francoisdabonot.firebasekmpdemo"
    compileSdk =
        libs.versions.android.compileSdk
            .get()
            .toInt()

    defaultConfig {
        applicationId = "fr.francoisdabonot.firebasekmpdemo"
        minSdk =
            libs.versions.android.minSdk
                .get()
                .toInt()
        targetSdk =
            libs.versions.android.targetSdk
                .get()
                .toInt()
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

swiftPackageConfig {
    // a list of SPM package using by Firebase
    val localDeps = firebaseDeps
    create("nativeExample") {
        minMacos = "10.15"
        dependency {
            remotePackageVersion(
                // Repository URL
                url = uri("https://github.com/firebase/firebase-ios-sdk.git"),
                // Libraries from the package
                products = {
                    // Export to Kotlin for use in shared Kotlin code and use it in your swift code
                    // the export doesn't work when gitlive is implemented, my guess is a bug with cinterop
                    // because gitlive already use cinterop
                    localDeps.forEach { add(it, exportToKotlin = false) }
                },
                // (Optional) Package name, can be required in some cases
                packageName = "firebase-ios-sdk",
                // Package version
                version = "11.6.0",
            )
        }
    }
}
