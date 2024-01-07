import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel : ViewModel() {
    private val _screenStateFlow: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.SPLASHSCREEN)
    private val screenStack : ArrayDeque<ScreenState> = ArrayDeque()
    val screenStateFlow: StateFlow<ScreenState> get() = _screenStateFlow.asStateFlow()

    private fun setAppState(screen: ScreenState) {
        _screenStateFlow.value = screen
    }

    fun navigateToLastScreen() {
        if (screenStack.isEmpty()) {
            println("Screen Stack is empty!")
            return
        }
        setAppState(screenStack.last())
        screenStack.removeLast()
    }

    fun navigateToAnotherScreen(screen: ScreenState, maintainHistory : Boolean = false) {
        if (maintainHistory)
            addToScreenStack(currentScreen())
        setAppState(screen)
    }

    fun currentScreen(): ScreenState {
        return screenStateFlow.value
    }

    private fun addToScreenStack(screen: ScreenState) {
        screenStack.add(screen)
    }
}