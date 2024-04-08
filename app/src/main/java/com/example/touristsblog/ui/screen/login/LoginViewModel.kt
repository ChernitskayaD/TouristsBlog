package com.example.touristsblog.ui.screen.login

import androidx.lifecycle.viewModelScope
import com.example.touristsblog.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.touristsblog.navigation.routing.generatePath
import com.example.touristsblog.navigation.Routes
import com.example.touristsblog.network.SignInUseCase
import com.example.touristsblog.network.UserSignIn
import com.example.touristsblog.network.UserSignUp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
) : BaseViewModel() {

    private val mUsernameState = MutableStateFlow("")
    val usernameState: StateFlow<String>
        get() = mUsernameState

    fun onChangeUsername(username: String) {
        mUsernameState.value = username
    }

    fun onClickLogin(email: String, password: String) = viewModelScope.launch {
        val result = signInUseCase.invoke(UserSignIn(email, password))
        if (result.respone != "ERROR") {
            withContext(Dispatchers.Main) {
                navigateTo(
                    Routes.Profile.generatePath()
                )
            }
        }
    }

    fun onClickSignUp() = viewModelScope.launch {
        navigateTo(
            Routes.SignUp.generatePath()
        )
    }
}