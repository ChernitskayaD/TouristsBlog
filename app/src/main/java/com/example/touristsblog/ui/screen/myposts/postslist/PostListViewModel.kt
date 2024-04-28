package com.example.touristsblog.ui.screen.myposts.postslist

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.viewModelScope
import com.example.touristsblog.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.touristsblog.navigation.routing.generatePath
import com.example.touristsblog.navigation.Routes
import com.example.touristsblog.network.myposts.CreatePostUseCase
import com.example.touristsblog.network.myposts.MyPostsUseCase
import com.example.touristsblog.network.myposts.model.requests.MyPostsRequest
import com.example.touristsblog.network.myposts.model.response.ShortPlaceInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val myPostsUseCase: MyPostsUseCase,
    private val prefs: DataStore<Preferences>,
) : BaseViewModel() {
    private val userSessionKey = stringPreferencesKey("user_session")
    private val defaultImage = "https://travelswm.com/wp-content/uploads/2018/02/Vecherom-Moskva.jpg"
    private val mPostsState = MutableStateFlow(
        listOf<PostPreview>(
        )
    )
    val postsSate: StateFlow<List<PostPreview>>
        get() = mPostsState

    init {
        getMyPosts()
    }

    fun addItem(item: PostPreview) {
        val tmp = mPostsState.value.toMutableList()
        tmp.add(item)
        viewModelScope.launch {
            mPostsState.emit(tmp.toList())
        }
    }

    fun removeItem(itemId: String) {
        val tmp = mPostsState.value.toMutableList()
        tmp.removeIf { it.postId == itemId }
        viewModelScope.launch {
            mPostsState.emit(tmp.toList())
        }
    }

    fun openPost(postId: String) {
        viewModelScope.launch {
            navigateTo(
                Routes.ViewPost.generatePath(
                    "postId" to postId
                )
            )
        }
    }

    private fun getMyPosts() {
        viewModelScope.launch {
            val authorId = prefs.data.map {
                it[userSessionKey]
            }.firstOrNull() ?: "0"
            authorId.toIntOrNull()?.let { id ->
                val posts = myPostsUseCase.invoke(MyPostsRequest(id))
                posts.posts.forEach {
                    addItem(it.mapPost())
                }
            }
        }
    }

    private fun ShortPlaceInfo.mapPost() = PostPreview(
        postId = postId.toString(),
        postTitle = postTitle,
        postGeo = postGeo,
        postImage = if (postPic.isNotBlank()) {
            "https://turblogbek-rodya.amvera.io/image/$postPic"
        } else {
            defaultImage
        },
        creationDate = creationDate
    )

    fun onClickCreateNewPost() = viewModelScope.launch {
        navigateTo(
            Routes.CreatePost.generatePath()
        )
    }
}