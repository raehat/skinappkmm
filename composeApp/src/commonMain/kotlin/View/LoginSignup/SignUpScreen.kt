package View.LoginSignup

import ScreenState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SignUp(
    currentScreen: () -> ScreenState,
    navigateToAnotherScreen: (ScreenState, Boolean) -> Unit
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

            HeaderSignInUp(currentScreen)
            EmailTextField()
            PasswordTextField()
            SignInUpButton(currentScreen, navigateToAnotherScreen)
            DividerWithText()
            Row(
                modifier = Modifier.fillMaxWidth(0.5f)
                    .padding(top = 40.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                GoogleLoginSignUp(currentScreen)
                FacebookLoginSignUp(currentScreen)
            }
            LoginSignUpClickableText(currentScreen, navigateToAnotherScreen)

        }
    }
}