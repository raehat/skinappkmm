package View.LoginSignup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UpdatePassword(
    navigateToLastScreen: () -> Unit
) {
    Column {
        BackButton(navigateToLastScreen)
        Header("New Password", "Your new password must be different from all your previously used passwords")
        Column(
            modifier = Modifier
                .padding(top = 20.dp)
        ) {
            PasswordTextField()
            PasswordTextField("Confirm Password")
        }
        TwachaButton("Create New Password") {}
    }
}