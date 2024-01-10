package SharedPreferenceManager

import androidx.compose.runtime.Composable

expect class SharedPreferenceManagerFactory() {
    @Composable
    fun createSharedPreferenceManager() : SharedPreferencesManager
}