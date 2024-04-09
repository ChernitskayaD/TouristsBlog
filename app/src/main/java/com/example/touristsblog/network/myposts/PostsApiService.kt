package com.example.touristsblog.network.myposts

import com.example.touristsblog.network.model.DefaultResponse
import com.example.touristsblog.network.myposts.model.CreatePostDto
import com.example.touristsblog.network.user.model.UserSignInDto
import com.example.touristsblog.network.user.model.UserSignUpDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface PostsApiService {
    @POST("posts/save")
    suspend fun savePost(@Body body: CreatePostDto): DefaultResponse

    @POST("posts/myposts")
    suspend fun getMyPosts(@Body body: UserSignUpDto): DefaultResponse
    @POST("posts/feed")
    suspend fun getFeed(@Body body: UserSignUpDto): DefaultResponse
    @POST("posts/change-visibility")
    suspend fun changePostVisibility(@Body body: UserSignUpDto): DefaultResponse
    @DELETE("posts/delete")
    suspend fun deletePost(@Body body: UserSignUpDto): DefaultResponse
}