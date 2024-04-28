package com.example.touristsblog.network.myposts

import com.example.touristsblog.network.model.DefaultResponse
import com.example.touristsblog.network.model.GetPostDto
import com.example.touristsblog.network.model.IsVisibleDto
import com.example.touristsblog.network.myposts.model.requests.CreatePostDto
import com.example.touristsblog.network.myposts.model.requests.MyPostsRequest
import com.example.touristsblog.network.myposts.model.response.PostListResponse
import com.example.touristsblog.network.myposts.model.response.PostResponse
import okhttp3.MultipartBody

interface PostsRepository {
    suspend fun createPost(createPost: CreatePostDto): DefaultResponse

    suspend fun loadImage(file: MultipartBody.Part): String

    suspend fun getMyPosts(createPost: MyPostsRequest): PostListResponse

    suspend fun getPost(getPost: GetPostDto): PostResponse

    suspend fun deletePost(deletePost: GetPostDto): DefaultResponse

    suspend fun changePostVisibility(isVisibleDto: IsVisibleDto): DefaultResponse
    suspend fun getFeed(): PostListResponse
}