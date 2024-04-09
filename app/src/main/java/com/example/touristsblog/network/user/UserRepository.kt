package com.example.touristsblog.network.user

import com.example.touristsblog.network.model.DefaultResponse
import com.example.touristsblog.network.user.model.UserSignIn
import com.example.touristsblog.network.user.model.UserSignUp

interface UserRepository {
    suspend fun signIn(userSignIn: UserSignIn): DefaultResponse
    suspend fun signUp(userSignUp: UserSignUp): DefaultResponse
}