package com.example.touristsblog.ui.screen.myposts.postslist

import androidx.lifecycle.viewModelScope
import com.example.touristsblog.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.touristsblog.navigation.routing.generatePath
import com.example.touristsblog.navigation.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor() : BaseViewModel() {

    private val mPostsState = MutableStateFlow(
        listOf<PostPreview>(
            PostPreview(
                postId = "test",
                postTitle = "TEST TITLE",
                postGeo = "Moscow, Dom 1",
                postImage = "https://travelswm.com/wp-content/uploads/2018/02/Vecherom-Moskva.jpg",
                creationDate = "01.01.2024",
            )
        )
    )
    val postsSate: StateFlow<List<PostPreview>>
        get() = mPostsState

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

    fun onClickCreateNewPost() = viewModelScope.launch {
        navigateTo(
            Routes.CreatePost.generatePath()
        )
    }
}