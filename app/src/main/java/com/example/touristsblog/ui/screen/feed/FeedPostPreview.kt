package com.example.touristsblog.ui.screen.feed

data class FeedPostPreview(
    val postId: String,
    val postTitle: String?,
    val postGeo: String?,
    val postImage: String?,
    val author: String?,
    val creationDate: String?,
)
