package com.example.touristsblog.network.myposts.model.requests

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class CreatePostDto(
    @SerializedName("authorId")
    val authorId: Int,
    @SerializedName("postTitle")
    val postTitle: String,
    @SerializedName("postPic")
    val postPic: String?,
    @SerializedName("creationDate")
    val creationDate: String,
    @SerializedName("postGeo")
    val postGeo: String?,
    @SerializedName("content")
    val content: List<PostItemDto>,
)

data class PostItemDto(
    @SerializedName("itemPosition")
    val itemPosition: Int,
    @SerializedName("value")
    val value: String,
    @SerializedName("itemType")
    val itemType: String
)

data class CreatePost(
    val authorId: Int,
    val postTitle: String,
    val postPic: String,
    val creationDate: LocalDateTime,
    val postGeo: String,
    val content: List<PostItemDto>,
)