package com.example.touristsblog.network.myposts.model.requests

import com.google.gson.annotations.SerializedName

data class MyPostsRequest(
    @SerializedName("authorId")
    val authorId: Int,
)