package View

import AppState
import AppViewModel
import View.LoginSignup.SignIn
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun AppScreen(viewModel: AppViewModel) {
    val appState by viewModel.appStateFlow.collectAsState()

    Crossfade(targetState = appState) { targetState ->
        AnimatedVisibility(
            visible = targetState == AppState.SPLASHSCREEN,
            enter = fadeIn() + slideInHorizontally(),
            exit = fadeOut() + slideOutHorizontally()
        ) {
            SplashScreen(viewModel::navigateToAnotherScreen)
        }

        AnimatedVisibility(
            visible = targetState == AppState.HOMESCREEN,
            enter = fadeIn() + slideInHorizontally(),
            exit = fadeOut() + slideOutHorizontally()
        ) {
            HomeScreen(viewModel::navigateToAnotherScreen)
        }

        AnimatedVisibility(
            visible = targetState == AppState.SIGNINSCREEN,
            enter = fadeIn() + slideInHorizontally(),
            exit = fadeOut() + slideOutHorizontally()
        ) {
            SignIn(viewModel::navigateToAnotherScreen)
        }
    }
}
