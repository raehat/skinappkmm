package View.HomeScreen

import Data.AnalysisResult
import Data.Network
import Network.addNewScan
import Network.analyzeImage
import PhotoSelector.ImagePicker
import PhotoSelector.rememberBitmapFromBytes
import ScreenState
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
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlin.io.encoding.ExperimentalEncodingApi

@Composable
fun ScanImageScreen(
    modifier: Modifier,
    imagePicker: ImagePicker,
    setImagePickedForAnalysis: (ByteArray) -> Unit,
    setAnalysisResult: (AnalysisResult) -> Unit,
    navigateToAnotherScreen: (ScreenState, Boolean) -> Unit,
    getEmail: () -> String?
) {
    Box(
        modifier = modifier
            .background(AppColor.LIGHT_GRAY)
    ) {
        var imagePicked by remember { mutableStateOf(byteArrayOf()) }
        var error by remember { mutableStateOf("") }
        imagePicker.registerPicker {
            imagePicked = it
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            UploadInstruction()
            PickImageCard(imagePicker, imagePicked)
            AnalyzeImageButton(
                imagePicked,
                setImagePickedForAnalysis,
                setAnalysisResult,
                navigateToAnotherScreen,
                getEmail
            ) {
                error = it
            }
            ChangeImageButton(imagePicker)
            ErrorText(error)
            AboutProcess()
        }
    }
}

@Composable
fun ErrorText(error: String) {
    Text(
        text = error
    )
}

@Composable
fun ChangeImageButton(
    imagePicker: ImagePicker
) {
    TwachaButton(
        modifier = Modifier.padding(20.dp, 20.dp, 20.dp, 0.dp),
        buttonText = "Change Image"
    ) { imagePicker.pickImage() }
}

@Composable
fun AnalyzeImageButton(
    imagePicked: ByteArray,
    setImagePickedForAnalysis: (ByteArray) -> Unit,
    setAnalysisResult: (AnalysisResult) -> Unit,
    navigateToAnotherScreen: (ScreenState, Boolean) -> Unit,
    getEmail: () -> String?,
    error: (String) -> Unit
) {
    var buttonText by remember { mutableStateOf("Analyze Image") }
    var buttonBackgroundColor by remember { mutableStateOf(AppColor.PURPLE) }

    TwachaButton(
        modifier = Modifier.padding(20.dp, 20.dp, 20.dp, 0.dp),
        buttonText = buttonText,
        backgroundColor = buttonBackgroundColor,
        buttonOnClick = {
            println("button clicked")
            onAnalyzeImageButtonClick(
                imagePicked = imagePicked,
                buttonText = { buttonTextChanged ->
                    buttonText = buttonTextChanged
                },
                buttonBackgroundColor = { colorChanged ->
                    buttonBackgroundColor = colorChanged
                },
                setImagePickedForAnalysis = setImagePickedForAnalysis,
                setAnalysisResult = setAnalysisResult,
                navigateToAnotherScreen = navigateToAnotherScreen,
                getEmail = getEmail
            ) {
                error(it)
            }
        }
    )
}

fun onAnalyzeImageButtonClick(
    imagePicked: ByteArray,
    buttonText: (String) -> Unit,
    buttonBackgroundColor: (Color) -> Unit,
    setImagePickedForAnalysis: (ByteArray) -> Unit,
    setAnalysisResult: (AnalysisResult) -> Unit,
    navigateToAnotherScreen: (ScreenState, Boolean) -> Unit,
    getEmail: () -> String?,
    error: (String) -> Unit,
) {
    if (!imagePicked.contentEquals(byteArrayOf())) {
        buttonText("Analyzing Image, may take a moment")
        buttonBackgroundColor(Color.Gray)

        CoroutineScope(Dispatchers.IO).launch {
            val response = analyzeImage(imagePicked)
            if (response != null && Network.isSuccessfulResponse(response.status)) {
                buttonText("Analyze Image")
                buttonBackgroundColor(AppColor.PURPLE)
                val result = Json.decodeFromString<AnalysisResult>(response.bodyAsText())

                setImagePickedForAnalysis(imagePicked)
                setAnalysisResult(result)
                addNewScan(
                    email = getEmail().toString(),
                    image = imagePicked,
                    analysisResult = result.`class`
                )

                navigateToAnotherScreen(ScreenState.ANALYSISRESULTSCREEN, true)
            } else {
                buttonText("Analyze Image")
                buttonBackgroundColor(AppColor.PURPLE)

                error("Couldn't reach Server. Please try again")
            }
        }
    }
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
    Card(
        modifier = Modifier
        .clip(shape = RoundedCornerShape(0.dp, 0.dp, 30.dp, 30.dp))
        .padding(bottom = 20.dp)
    ) {
        Text(
            text = "Click on the icon below to upload image of skin area to be diagnosed",
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 20.dp),
            textAlign = TextAlign.Center
        )
    }
}
