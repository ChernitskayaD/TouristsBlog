package com.example.touristsblog.network.user

import com.example.touristsblog.network.user.model.UserSignUp
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(userSignUp: UserSignUp) = repository.signUp(userSignUp)
}
