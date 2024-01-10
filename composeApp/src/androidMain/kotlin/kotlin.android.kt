//package Data
//
//import android.app.Application
//import android.content.Context.MODE_APPEND
//import android.app.Activity
//import android.content.Intent
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.os.Bundle
//import android.provider.MediaStore
//import kotlinx.coroutines.suspendCancellableCoroutine
//import kotlin.coroutines.resume
//
//actual class SharedPreferencesManager actual constructor() {
//    private val sharedPreferences = Application().applicationContext.getSharedPreferences("MySharedPref", MODE_APPEND)
//
//        actual fun getString(key: String): String? {
//        return sharedPreferences.getString(key, null)
//    }
//
//    actual fun putString(key: String, value: String) {
//        sharedPreferences.edit().putString(key, value).apply()
//    }
//}
//
//actual class PhotoSelector(private val activity: Activity) {
//    private var continuation: ((Bitmap?) -> Unit)? = null
//
//    suspend fun selectPhoto(): Bitmap? {
//        return suspendCancellableCoroutine { cont ->
//            continuation = cont
//            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//            val requestCode = 123 // You can choose any value
//
//            activity.startActivityForResult(intent, requestCode)
//        }
//    }
//
//    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (requestCode == 123 && resultCode == Activity.RESULT_OK) {
//            val selectedImageUri = data?.data
//            if (selectedImageUri != null) {
//                val inputStream = activity.contentResolver.openInputStream(selectedImageUri)
//                val bitmap = BitmapFactory.decodeStream(inputStream)
//                continuation?.invoke(bitmap)
//            } else {
//                continuation?.invoke(null)
//            }
//        } else {
//            continuation?.invoke(null)
//        }
//        continuation = null
//    }
//}