package SharedPreferenceManager

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

actual class SharedPreferenceManagerFactory {
    @Composable
    actual fun createSharedPreferenceManager() : SharedPreferencesManager {
        val context = LocalContext.current
        return SharedPreferencesManager(context)
    }
}
