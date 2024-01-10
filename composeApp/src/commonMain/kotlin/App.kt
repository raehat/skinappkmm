import SharedPreferenceManager.SharedPreferenceManagerFactory
import View.AppScreen
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@Composable
fun App() {
    val sharedPreferencesManager = SharedPreferenceManagerFactory().createSharedPreferenceManager()
    val viewModel = getViewModel(
        key = "app",
        factory = viewModelFactory {
            AppViewModel(sharedPreferencesManager)
        }
    )

    MaterialTheme {
        AppScreen(viewModel)
    }
}
