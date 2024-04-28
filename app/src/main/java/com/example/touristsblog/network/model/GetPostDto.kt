package com.example.touristsblog.network.model

import com.google.gson.annotations.SerializedName

data class GetPostDto(
    @SerializedName("postId") val postId: String
)
