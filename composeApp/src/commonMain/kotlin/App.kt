import Network.signUp
import View.AppScreen
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    val viewModel = getViewModel(
        key = "app",
        factory = viewModelFactory {
            AppViewModel()
        }
    )

    MaterialTheme {
        AppScreen(viewModel)
    }
}
