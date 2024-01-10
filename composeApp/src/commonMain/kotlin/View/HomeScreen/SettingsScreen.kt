package View.HomeScreen

import Theme.AppColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingsScreen(modifier: Modifier) {
    Box(
        modifier = modifier
            .background(AppColor.LIGHT_GRAY)
    ) {
        Text("SettingsScreen")
    }
}