package com.example.touristsblog.network.myposts

import com.example.touristsblog.network.model.DefaultResponse
import com.example.touristsblog.network.model.GetPostDto
import com.example.touristsblog.network.model.IsVisibleDto
import com.example.touristsblog.network.myposts.model.requests.CreatePostDto
import com.example.touristsblog.network.myposts.model.requests.MyPostsRequest
import com.example.touristsblog.network.myposts.model.response.PostListResponse
import com.example.touristsblog.network.myposts.model.response.PostResponse
import okhttp3.MultipartBody
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val service: PostsApiService
) : PostsRepository {
    override suspend fun createPost(createPost: CreatePostDto): DefaultResponse =
        service.savePost(createPost)

    override suspend fun loadImage(file: MultipartBody.Part) = service.uploadImage(file).imageName

    override suspend fun getMyPosts(createPost: MyPostsRequest): PostListResponse =
        service.getMyPosts(createPost)

    override suspend fun getFeed(): PostListResponse =
        service.getFeed()

    override suspend fun getPost(getPost: GetPostDto): PostResponse =
        service.getPost(getPost)

    override suspend fun deletePost(deletePost: GetPostDto): DefaultResponse =
        service.deletePost(deletePost)

    override suspend fun changePostVisibility(isVisibleDto: IsVisibleDto): DefaultResponse =
        service.changePostVisibility(isVisibleDto)
}