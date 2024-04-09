package com.example.touristsblog.network.myposts

import com.example.touristsblog.network.model.DefaultResponse
import com.example.touristsblog.network.myposts.model.CreatePostDto
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val service: PostsApiService
) : PostsRepository {
    override suspend fun createPost(createPost: CreatePostDto): DefaultResponse =
        service.savePost(createPost)

}