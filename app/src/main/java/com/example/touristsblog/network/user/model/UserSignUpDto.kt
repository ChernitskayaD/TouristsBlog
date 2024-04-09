package com.example.touristsblog.network.user.model

import com.google.gson.annotations.SerializedName

data class UserSignUpDto(
    @SerializedName("mail")
    val mail: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String
)

data class UserSignUp(
    val username: String,
    val mail: String,
    val password: String
)
