package fr.francoisdabonot.firebasekmpdemo

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
    override suspend fun prompt(message: String): String {
        return "Not available"
    }
}

actual fun getPlatform(): Platform = JVMPlatform()