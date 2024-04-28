package com.example.touristsblog.ui.screen.login

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.viewModelScope
import com.example.touristsblog.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.touristsblog.navigation.routing.generatePath
import com.example.touristsblog.navigation.Routes
import com.example.touristsblog.network.user.SignInUseCase
import com.example.touristsblog.network.user.model.UserSignIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val prefs: DataStore<Preferences>,
) : BaseViewModel() {
    private val userSessionKey = stringPreferencesKey("user_session")
    private val mErrorState = MutableStateFlow("")
    val errorState: StateFlow<String>
        get() = mErrorState

    fun onClickLogin(email: String, password: String) = viewModelScope.launch {
        val result = signInUseCase.invoke(UserSignIn(email, password))
        if (result.value != null) {
            withContext(Dispatchers.Main) {
                prefs.edit {
                    it[userSessionKey] = result.value
                }
                navigateTo(
                    Routes.Profile.generatePath()
                )
            }
        } else {
            mErrorState.emit(result.message)
        }
    }

    fun onClickSignUp() = viewModelScope.launch {
        navigateTo(
            Routes.SignUp.generatePath()
        )
    }
}