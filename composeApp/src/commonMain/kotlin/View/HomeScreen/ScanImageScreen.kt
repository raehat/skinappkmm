package View.HomeScreen

import PhotoSelector.ImagePicker
import PhotoSelector.rememberBitmapFromBytes
import Theme.AppColor
import View.LoginSignup.TwachaButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import com.example.twacha.MR
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale

@Composable
fun ScanImageScreen(modifier: Modifier, imagePicker: ImagePicker) {
    Box(
        modifier = modifier
            .background(AppColor.LIGHT_GRAY)
    ) {
        var imagePicked by remember { mutableStateOf(byteArrayOf()) }
        imagePicker.registerPicker {
            imagePicked = it
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            UploadInstruction()
            PickImageCard(imagePicker, imagePicked)
            AnalyzeImage(imagePicked)
            ChangeImage(imagePicker)
            AboutProcess()
        }
    }
}

@Composable
fun ChangeImage(
    imagePicker: ImagePicker
) {
    TwachaButton(
        modifier = Modifier.padding(20.dp, 20.dp, 20.dp, 0.dp),
        "Change Image"
    ) { imagePicker.pickImage() }
}

@Composable
fun AnalyzeImage(imagePicked: ByteArray) {
    TwachaButton(
        modifier = Modifier.padding(20.dp, 20.dp, 20.dp, 0.dp),
        "Analyze Image"
    ) {  }
}

@Composable
fun AboutProcess() {
    Card(
        modifier = Modifier
            .fillMaxHeight(0.9f)
            .fillMaxWidth(0.9f)
            .padding(top = 30.dp),
        shape = RoundedCornerShape(30.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .weight(1f),
                painter = dev.icerock.moko.resources.compose.painterResource(MR.images.ai),
                contentDescription = null
            )
            Text(
                text = "When you click on upload, our AI model will " +
                        "analyze the image and provide you with the diagnosed issue",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .weight(2f),
                textAlign = TextAlign.Left
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PickImageCard(imagePicker: ImagePicker, imagePicked: ByteArray) {
    Card(
        modifier = Modifier
            .fillMaxHeight(0.4f)
            .fillMaxWidth(0.6f),
        shape = RoundedCornerShape(30.dp),
        onClick = { imagePicker.pickImage() }
    ) {
        Icon(
            Icons.Default.Add,
            modifier = Modifier
                .size(100.dp),
            tint = Color.LightGray,
            contentDescription = null
        )
        val imageBitmap = if (!imagePicked.contentEquals(byteArrayOf())) rememberBitmapFromBytes(imagePicked) else null
        if (imageBitmap != null)
            Image(
                imageBitmap,
                modifier = Modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(30.dp)),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
    }
}

@Composable
fun UploadInstruction() {
    Text(
        text = "Click on the icon below to upload image of skin area to be diagnosed",
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 20.dp),
        textAlign = TextAlign.Center
    )
}
