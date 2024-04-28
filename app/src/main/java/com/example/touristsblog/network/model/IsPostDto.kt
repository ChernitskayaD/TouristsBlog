package com.example.touristsblog.network.model

import com.google.gson.annotations.SerializedName

data class IsVisibleDto(
    @SerializedName("postId") val postId: String,
    @SerializedName("isOpen") val isOpen: Boolean,
)
