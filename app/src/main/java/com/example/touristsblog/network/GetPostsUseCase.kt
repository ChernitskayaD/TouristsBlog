package com.example.touristsblog.network

import com.example.touristsblog.network.model.GetPostDto
import com.example.touristsblog.network.myposts.PostsRepository
import com.example.touristsblog.network.myposts.model.requests.CreatePostDto
import okhttp3.MultipartBody
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: PostsRepository
) {
    suspend operator fun invoke(getPostDto: GetPostDto) = repository.getPost(getPostDto)
}
