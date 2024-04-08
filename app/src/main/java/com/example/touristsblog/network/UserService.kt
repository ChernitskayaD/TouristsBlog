package com.example.touristsblog.network

import android.util.Log
import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Inject

data class DefaultResponse(
    @SerializedName("respone")
    val respone: String
)

data class UserSignIn(
    val username: String,
    val password: String
)

data class UserSignUp(
    val username: String,
    val mail: String,
    val password: String
)

interface UserRepository {
    suspend fun signIn(userSignIn: UserSignIn): DefaultResponse
    suspend fun signUp(userSignUp: UserSignUp): DefaultResponse
}

class SignInUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(userSignIn: UserSignIn) = repository.signIn(userSignIn)
}

class SignUpUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(userSignUp: UserSignUp) = repository.signUp(userSignUp)
}

class SignInResponse(
    @SerializedName("token")
    val token: String
)

class UserSignInDto(
    @SerializedName("mail")
    val username: String,
    @SerializedName("password")
    val password: String
)

class UserSignUpDto(
    @SerializedName("mail")
    val mail: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String
)

fun UserSignIn.fromDomain() = UserSignInDto(
    username,
    password
)

fun UserSignUp.fromDomain() = UserSignUpDto(
    username,
    mail,
    password,
)

interface SignInApiService {
    @POST("users/auth")
    suspend fun signIn(@Body body: UserSignInDto): DefaultResponse

    @POST("users/reg")
    suspend fun signUp(@Body body: UserSignUpDto): DefaultResponse
}

class UserRepositoryImpl @Inject constructor(
    private val service: SignInApiService
) : UserRepository {

    override suspend fun signIn(userSignIn: UserSignIn): DefaultResponse = service.signIn(userSignIn.fromDomain())

    override suspend fun signUp(userSignUp: UserSignUp): DefaultResponse = service.signUp(userSignUp.fromDomain())
}