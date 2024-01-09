package Data

import android.app.Application
import android.content.Context.MODE_APPEND

// AndroidSharedPreferencesManager.kt
actual class SharedPreferencesManager actual constructor() {
    private val sharedPreferences = Application().applicationContext.getSharedPreferences("MySharedPref", MODE_APPEND)

        actual fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    actual fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }
}
