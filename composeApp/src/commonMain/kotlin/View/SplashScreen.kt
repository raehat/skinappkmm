package View

import ScreenState
import Theme.AppColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navigateToAnotherScreen: (ScreenState) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize().background(AppColor.PURPLE)
    ) {
        Text(
            text = "Twacha",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 40.sp,
            modifier = Modifier.align(Alignment.Center)
        )

        LaunchedEffect(Unit) {
            delay(1000L) // 1000 milliseconds = 1 second
            navigateToAnotherScreen(ScreenState.HOMESCREEN)
        }
    }
}