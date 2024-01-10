package View.LoginSignup

import ScreenState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

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
            SignInUpButton(currentScreen, navigateToAnotherScreen, email, password, getEmail, setEmail, saveLoginEmail)
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