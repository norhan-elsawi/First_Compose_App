package com.example.firstcomposeapp.base

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstcomposeapp.utils.ContextProviders
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

data class ScreenState<STATE>(
    val state: STATE,
    val currentContext: Context,
    val isLoading: Boolean,
    val hasError: Boolean,
    val errorMessage: String,
)

@Composable
fun <STATE> StateComposable(
    vm: BaseComposeViewModel<STATE>,
    content: @Composable (screenState: ScreenState<STATE>) -> Unit,
) {
    val currentContext = LocalContext.current

    val state = vm.state.collectAsState()
    val isLoading = vm.isLoading.collectAsState()
    val errorMessage = vm.errorMessage.collectAsState()
    val hasError = vm.hasError()
    content(
        screenState = ScreenState(
            state = state.value,
            currentContext = currentContext,
            isLoading = isLoading.value,
            hasError = hasError,
            errorMessage = errorMessage.value,
        ),
    )
}


abstract class BaseComposeViewModel<STATE>(
    private val initialState: STATE,
    private val contextProviders: ContextProviders,
) : ViewModel() {

    val state = MutableStateFlow(initialState)
    val isLoading = MutableStateFlow(false)
    val errorMessage = MutableStateFlow("")
    val authRequired = MutableStateFlow(false)
    fun hasError() = errorMessage.value.isNotEmpty() && !authRequired.value

    fun resetState() = initialState.also { state.value = it }

    /**
     *  used for two scenarios :
     *  1 - set the screen Data state and reset ( loading , error , reAuth ) on success
     *  2 - change  ( loading , error , reAuth ) state
     */
    fun setState(
        loading: Boolean = false,
        error: String = "",
        reAuth: Boolean = false,
        newState: (STATE.() -> STATE)? = null,
    ) {
        isLoading.value = loading
        errorMessage.value = error
        authRequired.value = reAuth

        newState?.let {
            state.value = newState(state.value)
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is AuthException -> setState(reAuth = true, error = "Auth Required Login Again!")
            else -> setState(error = throwable.message.toString())
        }
    }

    fun launchBlock(emitLoading: Boolean = true, block: suspend CoroutineScope.() -> Unit) {
        setState(loading = emitLoading)
        viewModelScope.launch(contextProviders.Main + coroutineExceptionHandler) {
            block.invoke(this)
        }
    }

}