package com.example.touristsblog.network.myposts

import com.example.touristsblog.network.myposts.model.requests.CreatePostDto
import com.example.touristsblog.network.myposts.model.requests.MyPostsRequest
import javax.inject.Inject

class MyPostsUseCase @Inject constructor(
    private val repository: PostsRepository
) {
    suspend operator fun invoke(myPosts: MyPostsRequest) = repository.getMyPosts(myPosts)
}