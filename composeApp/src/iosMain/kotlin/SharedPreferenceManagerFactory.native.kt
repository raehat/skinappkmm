package SharedPreferenceManager

import androidx.compose.runtime.Composable

actual class SharedPreferenceManagerFactory {
    @Composable
    actual fun createSharedPreferenceManager() : SharedPreferencesManager {
        return SharedPreferencesManager()
    }
}