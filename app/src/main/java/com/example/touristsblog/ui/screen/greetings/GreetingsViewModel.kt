package com.example.touristsblog.ui.screen.greetings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.viewModelScope
import com.example.touristsblog.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.touristsblog.navigation.routing.generatePath
import com.example.touristsblog.navigation.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GreetingsViewModel @Inject constructor(
    private val prefs: DataStore<Preferences>,
) : BaseViewModel() {
    private val userSessionKey = stringPreferencesKey("user_session")

    init {
        viewModelScope.launch {
            val userSession = prefs.data.map {
                it[userSessionKey]
            }.firstOrNull() ?: "0"
            if (userSession != "0") {
                navigateTo(
                    Routes.Profile.generatePath()
                )
            }
        }
    }

    fun onClickLogin() = viewModelScope.launch {
        navigateTo(
            Routes.Login.generatePath()
        )
    }

    fun onClickSignUp() = viewModelScope.launch {
        navigateTo(
            Routes.SignUp.generatePath()
        )
    }
}