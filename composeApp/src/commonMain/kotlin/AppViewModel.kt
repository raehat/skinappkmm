import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel : ViewModel() {
    private val _appStateFlow: MutableStateFlow<AppState> = MutableStateFlow(AppState.SPLASHSCREEN)
    val appStateFlow: StateFlow<AppState> get() = _appStateFlow.asStateFlow()

    private fun setAppState(appState: AppState) {
        _appStateFlow.value = appState
    }

    fun navigateToAnotherScreen(appState: AppState) {
        setAppState(appState)
    }
}