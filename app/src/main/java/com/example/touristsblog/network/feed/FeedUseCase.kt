package com.example.touristsblog.network.feed

import com.example.touristsblog.network.model.GetPostDto
import com.example.touristsblog.network.myposts.PostsRepository
import com.example.touristsblog.network.myposts.model.requests.CreatePostDto
import okhttp3.MultipartBody
import javax.inject.Inject

class FeedUseCase @Inject constructor(
    private val repository: PostsRepository
) {
    suspend operator fun invoke() = repository.getFeed()

}
