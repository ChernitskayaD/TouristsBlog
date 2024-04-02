package com.example.touristsblog.ui.screen.profile

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
class ProfileViewModel @Inject constructor() : BaseViewModel() {

    private val mUsernameState = MutableStateFlow("")
    val usernameState: StateFlow<String>
        get() = mUsernameState

    fun onChangeUsername(username: String) {
        mUsernameState.value = username
    }

    fun onClickLogin() = viewModelScope.launch {
        navigateTo(
            Routes.CreatePost.generatePath())
    }
    fun onClickSignUp() = viewModelScope.launch {
        navigateTo(
            Routes.SignUp.generatePath()
        )
    }
}