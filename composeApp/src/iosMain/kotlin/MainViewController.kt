import PhotoSelector.ImagePicker
import PhotoSelector.ImagePickerFactory
import androidx.compose.ui.interop.LocalUIViewController
import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController { App(ImagePickerFactory(LocalUIViewController.current).createPicker()) }
