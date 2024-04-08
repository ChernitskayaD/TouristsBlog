package com.example.touristsblog.ui.screen.signup

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.touristsblog.BaseViewModel
import com.example.touristsblog.navigation.Routes
import com.example.touristsblog.navigation.routing.generatePath
import com.example.touristsblog.network.SignInUseCase
import com.example.touristsblog.network.SignUpUseCase
import com.example.touristsblog.network.UserSignIn
import com.example.touristsblog.network.UserSignUp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val signUpUseCase: SignUpUseCase,
) : BaseViewModel() {
    fun registration(name: String, email: String, password: String) {
        viewModelScope.launch {
            val result = signUpUseCase.invoke(UserSignUp(name, email, password))
            if (result.respone != "ERROR") {
                withContext(Dispatchers.Main) {
                    navigateTo(
                        Routes.Profile.generatePath()
                    )
                }
            }
        }
    }

    private val mUsernameState = MutableStateFlow(savedStateHandle.get<String>("username") ?: "")
    val usernameState: StateFlow<String>
        get() = mUsernameState
}