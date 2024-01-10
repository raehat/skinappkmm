package View.HomeScreen

import PhotoSelector.ImagePicker
import Theme.AppColor
import Theme.AppColor.LIGHT_GRAY
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun HomePage(imagePicker: ImagePicker) {
    var currentHomePageScreen by remember { mutableStateOf(Screen.USERSCREEN) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .background(LIGHT_GRAY)
    ) {
        Screen(
            modifier = Modifier.weight(8.7f),
            currentHomePageScreen,
            imagePicker
        )
        BottomNavigationDrawer(modifier = Modifier.weight(1.3f)) {
            currentHomePageScreen = it
        }
    }
}

@Composable
fun Screen(
    modifier: Modifier,
    currentHomePageScreen: Screen,
    imagePicker: ImagePicker
) {

    when(currentHomePageScreen) {
        Screen.USERSCREEN -> {
            UserScreen(modifier)
        }
        Screen.SCANIMAGESCREEN -> {
            ScanImageScreen(modifier, imagePicker)
        }
        Screen.SETTINGSSCREEN -> {
            SettingsScreen(modifier)
        }
    }
}

@Composable
fun BottomNavigationDrawer(modifier: Modifier, setCurrentHomePageScreen: (Screen) -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)),
    ) {
        Row(
            modifier = Modifier
                .background(Color.Transparent),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButtonWithText(
                "User",
                Icons.Default.Person,
                Screen.USERSCREEN,
                setCurrentHomePageScreen
            )
            IconButtonWithText(
                "Scan Image",
                Icons.Default.Add,
                Screen.SCANIMAGESCREEN,
                setCurrentHomePageScreen
            )
            IconButtonWithText(
                "Settings",
                Icons.Default.Settings,
                Screen.SETTINGSSCREEN,
                setCurrentHomePageScreen
            )
        }
    }
}

@Composable
fun IconButtonWithText(
    text: String,
    icon: ImageVector,
    screen: Screen,
    setCurrentHomePageScreen: (Screen) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when(screen) {
            Screen.SCANIMAGESCREEN -> {
                FloatingActionButton(
                    onClick = {
                        setCurrentHomePageScreen(screen)
                    },
                    modifier = Modifier.padding(vertical = 3.dp),
                    backgroundColor = AppColor.PURPLE
                ) {
                    Icon(imageVector = icon, contentDescription = null, tint = Color.White)
                }
            }
            else -> {
                IconButton(
                    onClick = {
                        setCurrentHomePageScreen(screen)
                    },
                    modifier = Modifier.padding(vertical = 3.dp)
                ) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            }
        }
        Text(text = text)
    }
}

enum class Screen {
    USERSCREEN,
    SCANIMAGESCREEN,
    SETTINGSSCREEN
}