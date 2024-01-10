package SharedPreferenceManager

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

// SharedModule.kt
actual class SharedPreferencesManager(private val context: Context) {
    actual fun getString(key: String): String? {
        val prefs: SharedPreferences =  context.getSharedPreferences("", MODE_PRIVATE)
        return prefs.getString(key, "")
    }

    actual fun putString(key: String, value: String) {
        val prefs: SharedPreferences = context.getSharedPreferences("", MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(key,value)
        editor.apply()
    }
}