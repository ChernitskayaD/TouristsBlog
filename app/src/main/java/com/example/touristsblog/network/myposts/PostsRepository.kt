package com.example.touristsblog.network.myposts

import com.example.touristsblog.network.model.DefaultResponse
import com.example.touristsblog.network.myposts.model.CreatePostDto

interface PostsRepository {
    suspend fun createPost(createPost: CreatePostDto): DefaultResponse

}