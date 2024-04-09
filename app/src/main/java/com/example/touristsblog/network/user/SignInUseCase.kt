package com.example.touristsblog.network.user

import com.example.touristsblog.network.user.model.UserSignIn
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(userSignIn: UserSignIn) = repository.signIn(userSignIn)
}
