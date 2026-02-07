package fr.francoisdabonot.firebasekmpdemo

import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.cinterop.ExperimentalForeignApi
import nativeExample.FirebaseAILogic
import nativeExample.FirebaseSdk
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

fun MainViewController() = ComposeUIViewController { App() }

val firebase = createFirebaseInitializer()

interface FirebaseInitializer {
    fun initialize()

    suspend fun prompt(message: String) : String
}

@OptIn(ExperimentalForeignApi::class)
fun createFirebaseInitializer(): FirebaseInitializer =
    object : FirebaseInitializer {

        private lateinit var aiLogic: FirebaseAILogic

        override fun initialize() {
            FirebaseSdk.configure()
            aiLogic = FirebaseAILogic()
        }

        @Throws(Exception::class)
        override suspend fun prompt(message: String): String {
            return suspendCoroutine { continuation ->
                aiLogic.prompt(message) { response, error ->
                    if (error != null) {
                        continuation.resumeWithException(Exception(error.toString()))
                    } else {
                        continuation.resume(response ?: "No text in response.")
                    }
                }
            }
        }
    }
