package com.example.touristsblog.network.user

import com.example.touristsblog.network.model.DefaultResponse
import com.example.touristsblog.network.user.model.UserSignInDto
import com.example.touristsblog.network.user.model.UserSignUpDto
import retrofit2.http.Body
import retrofit2.http.POST
interface UserApiService {
    @POST("users/auth")
    suspend fun signIn(@Body body: UserSignInDto): DefaultResponse

    @POST("users/reg")
    suspend fun signUp(@Body body: UserSignUpDto): DefaultResponse
}
