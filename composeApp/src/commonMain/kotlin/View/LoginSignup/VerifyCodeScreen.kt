package View.LoginSignup

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VerifyCodeScreen(
    navigateToLastScreen: () -> Unit
) {
    Column {
        BackButton(navigateToLastScreen)
        Header("Verify Code", "Please enter the code we just sent to example@email.com.", 20.dp)
        PinView()
        ResendOTPView()
    }
}

@Composable
fun ResendOTPView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Didn't receive OTP?",
            fontSize = 14.sp
        )
        Text(
            text = "Resend OTP",
            textDecoration = TextDecoration.Underline,
            fontSize = 14.sp,
            color = Color.Blue
        )
    }
}

@Composable
fun PinView() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
        ,
        horizontalArrangement = Arrangement.Center
    ) {
        OtpView(4, ::onPinValueChanged)
    }
}

private fun onPinValueChanged(newValue: String) {
    println("New PIN value: $newValue")
}

@Composable
fun BackButton(navigateToLastScreen: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .size(50.dp)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = CircleShape
            )
            .clickable {
                println("nigr")
                navigateToLastScreen()
            }
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}