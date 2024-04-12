package com.example.touristsblog.network.myposts

import com.example.touristsblog.network.model.DefaultResponse
import com.example.touristsblog.network.myposts.model.requests.CreatePostDto
import com.example.touristsblog.network.myposts.model.requests.MyPostsRequest
import com.example.touristsblog.network.myposts.model.response.PostListResponse
import com.example.touristsblog.network.myposts.model.response.UploadImageResponse
import com.example.touristsblog.network.user.model.UserSignUpDto
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query
import java.io.File

interface PostsApiService {
    @POST("posts/save")
    suspend fun savePost(@Body body: CreatePostDto): DefaultResponse
    @Multipart
    @POST("upload")
    suspend fun uploadImage(@Part file: MultipartBody.Part): UploadImageResponse

    @POST("posts/myposts")
    suspend fun getMyPosts(@Body body: MyPostsRequest): PostListResponse

    @POST("posts/feed")
    suspend fun getFeed(@Body body: UserSignUpDto): DefaultResponse

    @POST("posts/change-visibility")
    suspend fun changePostVisibility(@Body body: UserSignUpDto): DefaultResponse

    @DELETE("posts/delete")
    suspend fun deletePost(@Body body: UserSignUpDto): DefaultResponse
}