package com.example.touristsblog.network.myposts

import com.example.touristsblog.network.model.DefaultResponse
import com.example.touristsblog.network.model.GetPostDto
import com.example.touristsblog.network.model.IsVisibleDto
import com.example.touristsblog.network.myposts.model.requests.CreatePostDto
import com.example.touristsblog.network.myposts.model.requests.MyPostsRequest
import com.example.touristsblog.network.myposts.model.response.PostListResponse
import com.example.touristsblog.network.myposts.model.response.PostResponse
import com.example.touristsblog.network.myposts.model.response.UploadImageResponse
import com.example.touristsblog.network.user.model.UserSignUpDto
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part

interface PostsApiService {
    @POST("posts/save")
    suspend fun savePost(@Body body: CreatePostDto): DefaultResponse

    @Multipart
    @POST("upload")
    suspend fun uploadImage(@Part file: MultipartBody.Part): UploadImageResponse

    @POST("posts/myposts")
    suspend fun getMyPosts(@Body body: MyPostsRequest): PostListResponse

    @GET("posts/feed")
    suspend fun getFeed(): PostListResponse

    @PUT("posts/change-visibility")
    suspend fun changePostVisibility(@Body body: IsVisibleDto): DefaultResponse

    @POST("posts/get")
    suspend fun getPost(@Body body: GetPostDto): PostResponse

    @HTTP(method = "DELETE", path = "posts/delete", hasBody = true)
    suspend fun deletePost(@Body body: GetPostDto): DefaultResponse
}