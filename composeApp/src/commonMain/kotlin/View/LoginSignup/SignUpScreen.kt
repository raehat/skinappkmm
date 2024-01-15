package View.LoginSignup

import ScreenState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun SignUp(
    currentScreen: () -> ScreenState,
    navigateToAnotherScreen: (ScreenState, Boolean) -> Unit,
    getEmail: () -> String,
    setEmail: (String) -> Unit,
    saveLoginEmail: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        var isProgressIndicatorVisible by remember { mutableStateOf(false) }
        val strokeWidth = 5.dp

        if (isProgressIndicatorVisible) {
            CircularProgressIndicator(
                modifier = Modifier.drawBehind {
                    drawCircle(
                        Color.Blue,
                        radius = size.width / 2 - strokeWidth.toPx() / 2,
                        style = Stroke(strokeWidth.toPx())
                    )
                }
                    .align(Alignment.Center),
                color = Color.LightGray,
                strokeWidth = strokeWidth
            )
        }

        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            HeaderSignInUp(currentScreen)
            EmailTextField() {
                email = it
            }
            PasswordTextField() {
                password = it
            }
            SignInUpButton(currentScreen, navigateToAnotherScreen, email, password, getEmail, setEmail, saveLoginEmail
            ) { isProgressIndicatorVisible = it }
            DividerWithText()
//            Row(
//                modifier = Modifier.fillMaxWidth(0.5f)
//                    .padding(top = 40.dp),
//                horizontalArrangement = Arrangement.SpaceEvenly,
//            ) {
//                GoogleLoginSignUp(currentScreen)
//                FacebookLoginSignUp(currentScreen)
//            }
            LoginSignUpClickableText(currentScreen, navigateToAnotherScreen)

        }
    }
}