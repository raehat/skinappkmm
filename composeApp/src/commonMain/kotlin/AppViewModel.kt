import SharedPreferenceManager.SharedPreferencesManager
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel(private val sharedPreferencesManager: SharedPreferencesManager) : ViewModel() {
    private val _screenStateFlow: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.HOMEPAGE)
    private val screenStack : ArrayDeque<ScreenState> = ArrayDeque()
    private var email : String = ""
    private var otp: String = ""
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

    fun lastScreen(): ScreenState? {
        if (screenStack.isEmpty()) {
            println("Screen Stack is Empty!")
            return null
        }
        return screenStack.last()
    }

    private fun addToScreenStack(screen: ScreenState) {
        screenStack.add(screen)
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getEmail() : String {
        return email
    }

    fun getOTP(): String {
        return otp
    }

    fun setOTP(otp: String) {
        this.otp = otp
    }

    fun saveStringToDevice(key: String, value: String) {
        sharedPreferencesManager.putString(key, value)
    }

    fun getStringFromDevice(key: String): String? {
        return sharedPreferencesManager.getString(key)
    }
}