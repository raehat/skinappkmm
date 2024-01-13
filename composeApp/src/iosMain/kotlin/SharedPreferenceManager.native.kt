package SharedPreferenceManager
import platform.Foundation.NSUserDefaults

actual class SharedPreferencesManager {
    private val userDefaults: NSUserDefaults = NSUserDefaults.standardUserDefaults

    actual fun getString(key: String): String? {
        return userDefaults.stringForKey(key) ?: ""
    }

    actual fun putString(key: String, value: String) {
        userDefaults.setObject(value, key)
        userDefaults.synchronize()
    }
}