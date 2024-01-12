package View.HomeScreen

import Data.AnalysisResult
import View.LoginSignup.BackButton
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AnalysisScreen(
    getImagePickedForAnalysis: () -> ByteArray,
    getAnalysisResult: () -> AnalysisResult,
    navigateToLastScreen: () -> Unit
) {
    Column{
        BackButton { navigateToLastScreen() }
        ImageCard()
    }
}

@Composable
fun ImageCard() {
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
            Icon(
                Icons.Default.Add,
                modifier = Modifier,
                contentDescription = null
            )
        }
    }
}
