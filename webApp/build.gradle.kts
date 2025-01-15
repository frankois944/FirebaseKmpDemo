import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

version = "1.0.0-SNAPSHOT"
group = "com.example"

kotlin {
    js {
        browser {
            useCommonJs()
            commonWebpackConfig {
                outputFileName = "main.bundle.js"
            }
            runTask {
                sourceMaps = true
                devServer =
                    KotlinWebpackConfig.DevServer(
                        open = false,
                        port = 3000,
                        static = mutableListOf("${layout.buildDirectory.asFile.get()}/processedResources/js/main"),
                    )
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
        binaries.executable()
    }
    sourceSets {
        jsMain.dependencies {
            implementation(libs.firebase.analytics)
            implementation(libs.kotlinx.coroutines.core)
            implementation(npm("firebase", "10.12.2"))
        }

        jsTest.dependencies {
            implementation(kotlin("test-js"))
        }
    }
}
