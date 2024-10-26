package fr.francoisdabonot.firebasekmpdemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform