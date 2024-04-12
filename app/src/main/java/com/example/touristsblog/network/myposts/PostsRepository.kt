package com.example.touristsblog.network.myposts

import com.example.touristsblog.network.model.DefaultResponse
import com.example.touristsblog.network.myposts.model.requests.CreatePostDto
import com.example.touristsblog.network.myposts.model.requests.MyPostsRequest
import com.example.touristsblog.network.myposts.model.response.PostListResponse
import okhttp3.MultipartBody
import java.io.File

interface PostsRepository {
    suspend fun createPost(createPost: CreatePostDto): DefaultResponse

    suspend fun loadImage(file: MultipartBody.Part): String
    suspend fun getMyPosts(createPost: MyPostsRequest): PostListResponse

}