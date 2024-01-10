package View.HomeScreen

import PhotoSelector.ImagePicker
import Theme.AppColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ScanImageScreen(modifier: Modifier, imagePicker: ImagePicker) {
    Box(
        modifier = modifier
            .background(AppColor.LIGHT_GRAY)
    ) {
        imagePicker.registerPicker {

        }
        Text("ScanImageScreen")
        Button(
            onClick = {imagePicker.pickImage()},
        ) {
            Text("click me")
        }
    }
}