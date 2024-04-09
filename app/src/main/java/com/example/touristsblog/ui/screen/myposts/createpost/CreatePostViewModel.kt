package com.example.touristsblog.ui.screen.myposts.createpost

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.navArgument
import com.example.touristsblog.BaseViewModel
import com.example.touristsblog.network.myposts.CreatePostUseCase
import com.example.touristsblog.network.myposts.model.CreatePostDto
import com.example.touristsblog.network.myposts.model.PostItemDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val createPostUseCase: CreatePostUseCase,
    private val prefs: DataStore<Preferences>,
) : BaseViewModel() {
    private val userSessionKey = stringPreferencesKey("user_session")

    private var lastItemPos = 2

    private val mScreenState = MutableStateFlow(
        savedStateHandle.get<List<PostItem>>("screenstate") ?: listOf(
            PostItem(value = "Заголовок", itemPosition = 0, itemType = ItemType.TitleItem),
            PostItem(value = "Текст", itemPosition = 1, itemType = ItemType.TextItem),
            PostItem(value = "", itemPosition = 2, itemType = ItemType.ImageItem),
        )
    )
    val screenState: StateFlow<List<PostItem>>
        get() = mScreenState

    fun addItem(item: PostItem) {
        val tmp = mScreenState.value.toMutableList()
        tmp.add(item)
        viewModelScope.launch {
            mScreenState.emit(tmp.toList())
        }
    }

    fun removeItem(itemPosition: Int) {
        val tmp = mScreenState.value.toMutableList()
        tmp.removeIf { it.itemPosition == itemPosition }
        viewModelScope.launch {
            mScreenState.emit(tmp.toList())
        }
    }

    fun updateItemValue(itemPosition: Int, value: String) {
        val tmp = mScreenState.value.toMutableList()
        val item = tmp.find { it.itemPosition == itemPosition }?.copy(value = value)
        if (item != null) {
            removeItem(itemPosition)
            addItem(item)
        }

        viewModelScope.launch {
            mScreenState.emit(tmp.toList())
        }
    }

    fun getNextItemPos(): Int {
        return lastItemPos++
    }

    fun savePost() {
        viewModelScope.launch {
            val newPost = CreatePostDto(
                authorId = prefs.data.map {
                    it[userSessionKey]?.toInt()
                }.first() ?: 0,
                postTitle = mScreenState.value[0].value,
                postPic = mScreenState.value[2].value,
                creationDate = LocalDateTime.now(),
                postGeo = mScreenState.value.firstOrNull { it.itemType == ItemType.GeoItem }?.value,
                content = screenState.value.map {
                    PostItemDto(it.itemPosition, it.value, it.itemType.typeName)
                }
            )
            createPostUseCase.invoke(newPost)
        }
    }

    fun goBack() {
        navBack()
    }
}

