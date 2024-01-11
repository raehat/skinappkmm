package View.LoginSignup

import Network.checkOTPForgotPassword
import Network.verifyOTP
import ScreenState
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VerifyCodeScreen(
    navigateToAnotherScreen: (ScreenState) -> Unit,
    navigateToLastScreen: () -> Unit,
    getEmail: () -> String,
    lastScreen: () -> ScreenState?,
    setOTP: (String) -> Unit
) {
    Column {
        var pin by remember { mutableStateOf("") }
        var error by remember { mutableStateOf("") }

        BackButton(navigateToLastScreen)
        Header("Verify Code", "Please enter the code we just sent to example@email.com.", 20.dp)
        PinView() {
            pin = it
        }
        ResendOTPView()
        Text(
            text = error
        )
        TwachaButton(buttonText = "Verify") {
            when (lastScreen()) {
                ScreenState.SIGNINSCREEN -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        val success = checkOTPForgotPassword(
                            email = getEmail(),
                            otp = pin
                        )
                        if (success) {
                            setOTP(pin)
                            navigateToAnotherScreen(ScreenState.UPDATEPASSWORDSCREEN)
                        } else {
                            error = "Wrong OTP, try again"
                        }
                    }
                }
                ScreenState.SIGNUPSCREEN -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        val success = verifyOTP(
                            email = getEmail(),
                            otp = pin
                        )
                        if (success) {
                            navigateToAnotherScreen(ScreenState.HOMEPAGE)
                        } else {
                            error = "Wrong OTP, try again"
                        }
                    }
                }
                null -> TODO()
                else -> {}
            }
        }
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
fun PinView(onPinValueChanged: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
        ,
        horizontalArrangement = Arrangement.Center
    ) {
        OtpView(4) {
            onPinValueChanged(it)
        }
    }
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