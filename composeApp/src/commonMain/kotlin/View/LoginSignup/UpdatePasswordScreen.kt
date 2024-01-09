package View.LoginSignup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.*

@Composable
fun UpdatePassword(
    navigateToLastScreen: () -> Unit
) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column {
        BackButton(navigateToLastScreen)
        Header("New Password", "Your new password must be different from all your previously used passwords")
        Column(
            modifier = Modifier
                .padding(top = 20.dp)
        ) {
            PasswordTextField() {
                password = it
            }
            PasswordTextField("Confirm Password") {
                confirmPassword = it
            }
        }
        TwachaButton("Create New Password") {}
    }
}