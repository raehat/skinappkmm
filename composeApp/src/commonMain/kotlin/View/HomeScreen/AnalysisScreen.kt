package View.HomeScreen

import Data.AnalysisResult
import PhotoSelector.rememberBitmapFromBytes
import Theme.AppColor
import View.LoginSignup.BackButton
import View.LoginSignup.TwachaButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnalysisScreen(
    getImagePickedForAnalysis: () -> ByteArray,
    getAnalysisResult: () -> AnalysisResult,
    navigateToLastScreen: () -> Unit
) {
    val percentage = findProbabilityByClass(
        analysisResult = getAnalysisResult()
    ).toString()

    Box(
        modifier = Modifier
            .background(Color.White)
    ) {
        Column{
            BackButton { navigateToLastScreen() }
            ImageCard(getImagePickedForAnalysis())
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(top = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ConfidencePercentage(percentage = percentage)
                ResultText(getAnalysisResult().`class`)
            }
            LowConfidenceWarning(percentage = percentage)
            ReadMore()
        }
    }
}

@Composable
fun ReadMore() {
    TwachaButton(
        buttonText = "Read More"
    ) {  }
}

@Composable
fun LowConfidenceWarning(percentage: String) {
    val percent = percentage.substring(0, percentage.length - 1)
    if (percent.toFloat() < 80f) {
        Text(
            text = "*Confidence is too low. Please make sure to consult a doctor before reaching any conclusion",
            color = Color.Red,
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
    }
}

@Composable
fun ResultText(result: String) {
    Column {
        Text(
            text = "RESULT",
            fontWeight = FontWeight.Bold
        )
        Text(text = result)
    }
}

@Composable
fun ConfidencePercentage(percentage: String) {
    val percent = percentage.substring(0, percentage.length - 1)
    Column {
        Box(
            modifier = Modifier
        ) {
            ChartMaker(percent.toFloat().toInt(), 100 - percent.toFloat().toInt(), AppColor.PURPLE, Color.Gray)
            Text(
                text = "$percent%",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun ImageCard(imagePickedForAnalysis: ByteArray) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.4f),
            shape = RoundedCornerShape(40.dp)
        ) {
            val imageBitmap = if (!imagePickedForAnalysis.contentEquals(byteArrayOf()))
                rememberBitmapFromBytes(imagePickedForAnalysis)
            else
                null
            if (imageBitmap != null) {
                Image(
                    bitmap = imageBitmap,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

fun findProbabilityByClass(analysisResult: AnalysisResult, targetClass: String = analysisResult.`class`): String? {
    val matchingClass = analysisResult.probs.find { it.first() == targetClass }
    return matchingClass?.get(1)
}