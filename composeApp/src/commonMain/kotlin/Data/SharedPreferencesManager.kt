package Data

// SharedModule.kt
expect class SharedPreferencesManager() {
    fun getString(key: String): String?
    fun putString(key: String, value: String)
}
