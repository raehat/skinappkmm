package View

import ScreenState
import AppViewModel
import View.LoginSignup.SignIn
import View.LoginSignup.SignUp
import View.LoginSignup.VerifyCodeScreen
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
    val appState by viewModel.screenStateFlow.collectAsState()

    Crossfade(targetState = appState) { targetState ->
        AnimatedVisibility(
            visible = targetState == ScreenState.SPLASHSCREEN,
            enter = fadeIn() + slideInHorizontally(),
            exit = fadeOut() + slideOutHorizontally()
        ) {
            SplashScreen(viewModel::navigateToAnotherScreen)
        }

        AnimatedVisibility(
            visible = targetState == ScreenState.HOMESCREEN,
            enter = fadeIn() + slideInHorizontally(),
            exit = fadeOut() + slideOutHorizontally()
        ) {
            HomeScreen(viewModel::navigateToAnotherScreen)
        }

        AnimatedVisibility(
            visible = targetState == ScreenState.SIGNINSCREEN,
            enter = fadeIn() + slideInHorizontally(),
            exit = fadeOut() + slideOutHorizontally()
        ) {
            SignIn(viewModel::currentScreen, viewModel::navigateToAnotherScreen)
        }

        AnimatedVisibility(
            visible = targetState == ScreenState.SIGNUPSCREEN,
            enter = fadeIn() + slideInHorizontally(),
            exit = fadeOut() + slideOutHorizontally()
        ) {
            SignUp(viewModel::currentScreen, viewModel::navigateToAnotherScreen)
        }

        AnimatedVisibility(
            visible = targetState == ScreenState.VERIFYCODESCREEN,
            enter = fadeIn() + slideInHorizontally(),
            exit = fadeOut() + slideOutHorizontally()
        ) {
            VerifyCodeScreen(viewModel::navigateToLastScreen)
        }
    }
}
