package com.example.touristsblog.network.myposts

import com.example.touristsblog.network.model.DefaultResponse
import com.example.touristsblog.network.myposts.model.requests.CreatePostDto
import com.example.touristsblog.network.myposts.model.requests.MyPostsRequest
import com.example.touristsblog.network.myposts.model.response.PostListResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import java.io.File
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val service: PostsApiService
) : PostsRepository {
    override suspend fun createPost(createPost: CreatePostDto): DefaultResponse =
        service.savePost(createPost)

    override suspend fun loadImage(file: MultipartBody.Part) = service.uploadImage(file).imageName

    override suspend fun getMyPosts(createPost: MyPostsRequest): PostListResponse =
        service.getMyPosts(createPost)

}