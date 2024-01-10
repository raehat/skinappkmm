//package Data
//
//// iOSSharedPreferencesManager.kt
//import platform.Foundation.NSUserDefaults
//import platform.Foundation.setValue
//import platform.Foundation.stringForKey
//import platform.UIKit.UIImagePickerController
//import platform.UIKit.UIImagePickerControllerDelegateProtocol
//import platform.UIKit.UIImagePickerControllerSourceTypePhotoLibrary
//import platform.UIKit.UIViewController
//import kotlinx.cinterop.readValue
//import kotlinx.coroutines.suspendCancellableCoroutine
//import org.jetbrains.skia.Bitmap
//import platform.Foundation.NSData
//import platform.UIKit.UIImage
//import platform.UIKit.UIImageView
//import platform.darwin.NSObject
//import platform.darwin.select
//
//actual class SharedPreferencesManager actual constructor() {
//    private val userDefaults = NSUserDefaults.standardUserDefaults()
//
//    actual fun getString(key: String): String? {
//        return userDefaults.stringForKey(key)
//    }
//
//    actual fun putString(key: String, value: String) {
//        userDefaults.setValue(value, forKey = key)
//        userDefaults.synchronize()
//    }
//}
//
//actual class PhotoSelector actual constructor(private val viewController: UIViewController) {
//    actual suspend fun selectPhoto(): Bitmap? {
//        return suspendCancellableCoroutine { continuation ->
//            val imagePickerController = UIImagePickerController()
//            imagePickerController.sourceType = UIImagePickerControllerSourceTypePhotoLibrary
//
//            imagePickerController.delegate = object : UIImagePickerControllerDelegateProtocol {
//                override fun imagePickerController(
//                    picker: UIImagePickerController,
//                    didFinishPickingMediaWithInfo: Map<*, *>
//                ) {
//                    val selectedImage = didFinishPickingMediaWithInfo[UIImagePickerControllerOriginalImage] as? UIImage
//
//                    if (selectedImage != null) {
//                        val imageData = UIImageJPEGRepresentation(selectedImage, 1.0)
//                        val bitmap = BitmapFactory.decodeByteArray(imageData!!.bytes, 0, imageData.length.toInt())
//                        continuation.resume(bitmap)
//                    } else {
//                        continuation.resume(null)
//                    }
//
//                    picker.dismissViewControllerAnimated(true, null)
//                }
//
//                override fun imagePickerControllerDidCancel(picker: UIImagePickerController) {
//                    continuation.resume(null)
//                    picker.dismissViewControllerAnimated(true, null)
//                }
//            }
//
//            viewController.presentViewController(imagePickerController, animated = true, completion = null)
//        }
//    }
//}
