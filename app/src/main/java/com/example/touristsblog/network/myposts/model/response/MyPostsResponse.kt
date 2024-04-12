package com.example.touristsblog.network.myposts.model.response

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class PostListResponse(
    @SerializedName("posts")
    val posts: List<ShortPlaceInfo>
)

data class ShortPlaceInfo(
    @SerializedName("postId")
    val postId: Int,
    @SerializedName("postTitle")
    val postTitle: String,
    @SerializedName("postPic")
    val postPic: String,
    @SerializedName("authorName")
    val authorName: String,
    @SerializedName("creationDate")
    val creationDate: String,
    @SerializedName("postGeo")
    val postGeo: String?,
)

data class PostItem(
    @SerializedName("itemPosition")
    val itemPosition: Int,
    @SerializedName("value")
    val value: String,
    @SerializedName("itemType")
    val itemType: String
)