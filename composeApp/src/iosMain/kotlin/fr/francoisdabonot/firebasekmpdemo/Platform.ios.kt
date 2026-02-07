package fr.francoisdabonot.firebasekmpdemo


import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion

    @Throws(Exception::class)
    override suspend fun prompt(message: String): String {
       return firebase.prompt(message)
    }
}

actual fun getPlatform(): Platform = IOSPlatform()

