package com.example.touristsblog.ui.screen.feed

import androidx.lifecycle.viewModelScope
import com.example.touristsblog.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.touristsblog.navigation.routing.generatePath
import com.example.touristsblog.navigation.Routes
import com.example.touristsblog.network.feed.FeedUseCase
import com.example.touristsblog.network.myposts.model.requests.MyPostsRequest
import com.example.touristsblog.network.myposts.model.response.ShortPlaceInfo
import com.example.touristsblog.ui.screen.myposts.postslist.PostPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val feedUseCase: FeedUseCase,
) : BaseViewModel() {

    private val mPostsState = MutableStateFlow(
        listOf<FeedPostPreview>()
    )
    private val defaultImage = "https://travelswm.com/wp-content/uploads/2018/02/Vecherom-Moskva.jpg"

    init {
        viewModelScope.launch {
            val posts = feedUseCase.invoke()
            posts.posts.forEach {
                addItem(it.mapPost())
            }
        }
    }

    private fun ShortPlaceInfo.mapPost() = FeedPostPreview(
        postId = postId.toString(),
        postTitle = postTitle,
        postGeo = postGeo,
        postImage = if (postPic.isNotBlank()) {
            "https://turblogbek-rodya.amvera.io/image/$postPic"
        } else {
            defaultImage
        },
        creationDate = creationDate,
        author = authorName
    )

    val postsSate: StateFlow<List<FeedPostPreview>>
        get() = mPostsState

    fun addItem(item: FeedPostPreview) {
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

    fun onClickCreateNewPost() = viewModelScope.launch {
        navigateTo(
            Routes.CreatePost.generatePath()
        )
    }
}