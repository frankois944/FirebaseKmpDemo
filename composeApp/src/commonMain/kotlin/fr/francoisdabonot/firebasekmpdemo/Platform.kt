package fr.francoisdabonot.firebasekmpdemo

interface Platform {
    val name: String

    suspend fun prompt(message: String): String
}

expect fun getPlatform(): Platform