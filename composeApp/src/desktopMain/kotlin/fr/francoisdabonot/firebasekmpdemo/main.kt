package fr.francoisdabonot.firebasekmpdemo

import android.app.Application
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.google.firebase.FirebasePlatform
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseOptions
import dev.gitlive.firebase.initialize

// for full explanation https://github.com/GitLiveApp/firebase-java-sdk?tab=readme-ov-file#initializing-the-sdk
fun startFirebase() {
    FirebasePlatform.initializeFirebasePlatform(object : FirebasePlatform() {
        val storage = mutableMapOf<String, String>()
        override fun store(key: String, value: String) = storage.set(key, value)
        override fun retrieve(key: String) = storage[key]
        override fun clear(key: String) { storage.remove(key) }
        override fun log(msg: String) = println(msg)
    })
    Firebase.initialize(
        context = Application(),
        options = FirebaseOptions(
            applicationId = "1:986130107097:android:dedf68ae10bd13552fccc8",
            apiKey = "AIzaSyC_M8wKGHeVh0Rp6GvSoCtUxeGQUkx1mlQ",
            storageBucket = "fir-kmpdemo-6cde4.appspot.com",
            projectId = "fir-kmpdemo-6cde4",
        )
    )
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "FirebaseKmpDemo",
    ) {
        startFirebase()
        App()
    }
}
