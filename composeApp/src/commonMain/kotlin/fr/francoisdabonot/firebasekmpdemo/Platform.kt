package fr.francoisdabonot.firebasekmpdemo

interface Platform {
    val name: String

    @Throws(Exception::class)
    suspend fun prompt(message: String): String
}

expect fun getPlatform(): Platform