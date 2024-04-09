package com.example.touristsblog.network.user.model

import com.google.gson.annotations.SerializedName

data class UserSignInDto(
    @SerializedName("mail")
    val username: String,
    @SerializedName("password")
    val password: String
)

data class UserSignIn(
    val username: String,
    val password: String
)