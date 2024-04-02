package com.example.touristsblog.ui.screen.login

import androidx.lifecycle.viewModelScope
import com.example.touristsblog.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.touristsblog.navigation.routing.generatePath
import com.example.touristsblog.navigation.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {

    private val mUsernameState = MutableStateFlow("")
    val usernameState: StateFlow<String>
        get() = mUsernameState

    fun onChangeUsername(username: String) {
        mUsernameState.value = username
    }

    fun onClickLogin() = viewModelScope.launch {
        navigateTo(
            Routes.Profile.generatePath())
    }
    fun onClickSignUp() = viewModelScope.launch {
        navigateTo(
            Routes.SignUp.generatePath()
        )
    }
}