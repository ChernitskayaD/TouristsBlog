package com.example.touristsblog.network.myposts

import com.example.touristsblog.network.myposts.model.CreatePostDto
import javax.inject.Inject

class CreatePostUseCase @Inject constructor(
    private val repository: PostsRepository
) {
    suspend operator fun invoke(createPost: CreatePostDto) = repository.createPost(createPost)
}
