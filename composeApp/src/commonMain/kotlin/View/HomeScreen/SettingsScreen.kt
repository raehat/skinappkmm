package View.HomeScreen

import View.LoginSignup.TwachaButton
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen(logout: () -> Unit) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.87f)
    ) {
        Text(
            modifier = Modifier
                .padding(20.dp),
            text = "Settings",
            fontSize = 25.sp
        )

        Text(
            modifier = Modifier
                .padding(horizontal = 22.dp),
            text = "When you logout, you'll have to sign in again when you come back"
        )
        TwachaButton(
            buttonText = "Logout",
            modifier = Modifier.padding(20.dp, 10.dp, 20.dp, 0.dp)
        ) {
            logout()
        }
    }
}