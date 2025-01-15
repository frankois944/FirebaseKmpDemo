@file:OptIn(DelicateCoroutinesApi::class)

package fr.francoisdabonot.firebasekmpdemo

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseOptions
import dev.gitlive.firebase.analytics.analytics
import dev.gitlive.firebase.app
import dev.gitlive.firebase.initialize
import kotlinx.coroutines.DelicateCoroutinesApi

fun main() {
    println("Setup Firebase")
    Firebase.initialize(
        null,
        options =
            FirebaseOptions(
                applicationId = "1:986130107097:web:d56581f742ab68cc2fccc8",
                apiKey = "AIzaSyBiFMNJwP-XFw4s76mxxjTxoCKzqXUBwFk",
                storageBucket = "fir-kmpdemo-6cde4.firebasestorage.app",
                projectId = "fir-kmpdemo-6cde4",
            ),
    )
    println("setUserId")
    Firebase.analytics.setUserId("42")
    // println("setUserProperty") No working
    // Firebase.analytics.setUserProperty("prp1", "value1")
    println("logEvent")
    Firebase.analytics.logEvent("StartApp")
    println("App name ${Firebase.app.name}")
}
