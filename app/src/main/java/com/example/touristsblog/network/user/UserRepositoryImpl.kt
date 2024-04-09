package com.example.touristsblog.network.user

import com.example.touristsblog.network.model.DefaultResponse
import com.example.touristsblog.network.user.mapper.fromDomain
import com.example.touristsblog.network.user.model.UserSignIn
import com.example.touristsblog.network.user.model.UserSignUp
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val service: UserApiService
) : UserRepository {

    override suspend fun signIn(userSignIn: UserSignIn): DefaultResponse = service.signIn(userSignIn.fromDomain())

    override suspend fun signUp(userSignUp: UserSignUp): DefaultResponse = service.signUp(userSignUp.fromDomain())
}