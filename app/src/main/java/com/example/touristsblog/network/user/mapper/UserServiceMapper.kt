package com.example.touristsblog.network.user.mapper

import com.example.touristsblog.network.user.model.UserSignIn
import com.example.touristsblog.network.user.model.UserSignInDto
import com.example.touristsblog.network.user.model.UserSignUp
import com.example.touristsblog.network.user.model.UserSignUpDto

fun UserSignIn.fromDomain() = UserSignInDto(
    username = username,
    password = password
)

fun UserSignUp.fromDomain() = UserSignUpDto(
    mail = mail,
    name = username,
    password = password,
)