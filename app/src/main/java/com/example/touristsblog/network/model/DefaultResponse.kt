package com.example.touristsblog.network.model

import com.google.gson.annotations.SerializedName

data class DefaultResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("value")
    val value: String?
)
