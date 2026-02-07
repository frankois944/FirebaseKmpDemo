package fr.francoisdabonot.firebasekmpdemo

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Greeting {
    private val platform = getPlatform()

    init {
        GlobalScope.launch {
            try {
                // WARN: YOU NEED TO UPDATE YOUR PROJECT CONFIG TO MATCH WITH YOUR FIREBASE PROJECT
                // AI FEATURE NEED SPECIALS GOOGLE API RIGHTS
                // CHECK ERROR OUTPUT FOR MORE
                println("Start prompting : ${platform.name}")
                println(platform.prompt("Hello, ${platform.name}"))
            } catch (e: Exception) {
                println("Greeting error: ${e.message}")
            }
        }
    }

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}