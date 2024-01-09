package Data

// iOSSharedPreferencesManager.kt
import platform.Foundation.NSUserDefaults
import platform.Foundation.setValue
import platform.Foundation.stringForKey

actual class SharedPreferencesManager actual constructor() {
    private val userDefaults = NSUserDefaults.standardUserDefaults()

    actual fun getString(key: String): String? {
        return userDefaults.stringForKey(key)
    }

    actual fun putString(key: String, value: String) {
        userDefaults.setValue(value, forKey = key)
        userDefaults.synchronize()
    }
}
