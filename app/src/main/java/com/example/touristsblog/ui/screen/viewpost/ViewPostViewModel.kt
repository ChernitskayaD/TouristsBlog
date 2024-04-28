package com.example.touristsblog.ui.screen.viewpost

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.rememberNavController
import com.example.touristsblog.BaseViewModel
import com.example.touristsblog.network.DeletePostUseCase
import com.example.touristsblog.network.GetPostsUseCase
import com.example.touristsblog.network.UpdateVisibilityPostsUseCase
import com.example.touristsblog.network.model.GetPostDto
import com.example.touristsblog.network.model.IsVisibleDto
import com.example.touristsblog.network.myposts.CreatePostUseCase
import com.example.touristsblog.ui.screen.myposts.createpost.ItemType
import com.example.touristsblog.ui.screen.myposts.createpost.PostItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewPostViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getPostUseCase: GetPostsUseCase,
    private val updateVisibilityPostsUseCase: UpdateVisibilityPostsUseCase,
    private val deletePostUseCase: DeletePostUseCase,
) : BaseViewModel() {

    private val postId = savedStateHandle.get<String>("postId") ?: ""
    var isOpen = MutableStateFlow(false)
    private val mScreenState = MutableStateFlow(listOf<PostItem>())

    init {
        Log.i("TEST", postId)
        viewModelScope.launch {
            val response = getPostUseCase.invoke(GetPostDto(postId))
            isOpen.value = response.isOpen
            val content = response
                .content.map {
                    PostItem(
                        itemPosition = it.itemPosition, value = it.value, itemType = when (it.itemType) {
                            "Заголовок" -> ItemType.TitleItem
                            "Текст" -> ItemType.TextItem
                            "Изображение" -> ItemType.ImageItem
                            "Геометка" -> ItemType.GeoItem
                            else -> ItemType.TextItem
                        }
                    )
                }
            mScreenState.emit(content)
        }
    }

    val screenState: StateFlow<List<PostItem>>
        get() = mScreenState

    fun deletePost() {
        viewModelScope.launch {
            deletePostUseCase.invoke(GetPostDto(postId))
        }.invokeOnCompletion { navBack() }
    }

    fun changePostVisibility() {
        viewModelScope.launch {
            val result = updateVisibilityPostsUseCase.invoke(IsVisibleDto(postId, !isOpen.value))
            if (result.value != null) {
                isOpen.value = !isOpen.value
            }
        }
    }

    fun goBack() {
        navBack()
    }
}