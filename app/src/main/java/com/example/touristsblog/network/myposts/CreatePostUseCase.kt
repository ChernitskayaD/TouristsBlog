package com.example.touristsblog.network.myposts

import com.example.touristsblog.network.myposts.model.requests.CreatePostDto
import okhttp3.MultipartBody
import javax.inject.Inject

class CreatePostUseCase @Inject constructor(
    private val repository: PostsRepository
) {
    suspend operator fun invoke(createPost: CreatePostDto) = repository.createPost(createPost)
    suspend operator fun invoke(file: MultipartBody.Part) = repository.loadImage(file)
}
