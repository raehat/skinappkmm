//package Data
//
//import org.jetbrains.skia.Bitmap
//
//// SharedModule.kt
//expect class SharedPreferencesManager() {
//    fun getString(key: String): String?
//    fun putString(key: String, value: String)
//}
//
//expect class PhotoSelector()
//
//expect suspend fun PhotoSelector.selectPhoto(): Bitmap?
