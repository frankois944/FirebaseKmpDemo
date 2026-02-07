package fr.francoisdabonot.firebasekmpdemo

import android.os.Build
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend

class AndroidPlatform : Platform {

    private val model = Firebase.ai(
        backend =
            GenerativeBackend.googleAI()
    ).generativeModel("gemini-2.5-flash")

    override val name: String = "Android ${Build.VERSION.SDK_INT}"

    @Throws(Exception::class)
    override suspend fun prompt(message: String): String {
        return model.generateContent(message).text ?: "No text in response."
    }
}

actual fun getPlatform(): Platform = AndroidPlatform()