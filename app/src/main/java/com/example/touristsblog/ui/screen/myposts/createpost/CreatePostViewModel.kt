package com.example.touristsblog.ui.screen.myposts.createpost

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.touristsblog.BaseViewModel
import com.example.touristsblog.network.DadataBody
import com.example.touristsblog.network.DadataUseCase
import com.example.touristsblog.network.GetPostsUseCase
import com.example.touristsblog.network.model.GetPostDto
import com.example.touristsblog.network.myposts.CreatePostUseCase
import com.example.touristsblog.network.myposts.model.requests.CreatePostDto
import com.example.touristsblog.network.myposts.model.requests.PostItemDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getPostUseCase: GetPostsUseCase,
    private val createPostUseCase: CreatePostUseCase,
    private val dadataUseCase: DadataUseCase,
    private val prefs: DataStore<Preferences>,
) : BaseViewModel() {
    private val userSessionKey = stringPreferencesKey("user_session")
    private val postId = savedStateHandle.get<String>("postId") ?: ""

    private var lastItemPos = 3

    private val mScreenState = MutableStateFlow(
        savedStateHandle.get<List<PostItem>>("screenstate") ?: listOf(
            PostItem(value = "Заголовок", itemPosition = 0, itemType = ItemType.TitleItem),
            PostItem(value = "Текст", itemPosition = 1, itemType = ItemType.TextItem),
            PostItem(value = "", itemPosition = 2, itemType = ItemType.ImageItem),
            PostItem(value = "", itemPosition = 3, itemType = ItemType.GeoItem),
        )
    )
    val screenState: StateFlow<List<PostItem>>
        get() = mScreenState

    private val mAddressesState = MutableStateFlow(
        listOf("")
    )
    val addressState: StateFlow<List<String>>
        get() = mAddressesState

    init {
        if(postId != "") {
            viewModelScope.launch {
                val response = getPostUseCase.invoke(GetPostDto(postId))
                val content = response
                    .content.map {
                        PostItem(
                            itemPosition = it.itemPosition, value = it.value, itemType = when (it.itemType) {
                                "title" -> ItemType.TitleItem
                                "text" -> ItemType.TextItem
                                "image" -> ItemType.ImageItem
                                "geo" -> ItemType.GeoItem
                                else -> ItemType.TextItem
                            }
                        )
                    }
                mScreenState.emit(content)
            }
        }
    }
    fun searchAddress(query: String){
        viewModelScope.launch {
            val result = dadataUseCase.invoke(DadataBody(query, 5))
            mAddressesState.value = result.suggestions.map {
                it.unrestrictedValue
            }
        }
    }
    fun addItem(item: PostItem) {
        val tmp = mScreenState.value.toMutableList()
        tmp.add(item)
        tmp.sortBy { it.itemPosition }
        viewModelScope.launch {
            mScreenState.emit(tmp.toList())
        }
    }

    fun removeItem(itemPosition: Int) {
        val tmp = mScreenState.value.toMutableList()
        tmp.removeIf { it.itemPosition == itemPosition }
        tmp.sortBy { it.itemPosition }

        viewModelScope.launch {
            mScreenState.emit(tmp.toList())
        }
    }

    fun updateItemValue(itemPosition: Int, value: String) {
        val tmp = mScreenState.value.toMutableList()
        val item = tmp.find { it.itemPosition == itemPosition }?.copy(value = value)
        if (item != null) {
            tmp.removeIf { it.itemPosition == itemPosition }
            tmp.add(item)
        }
        tmp.sortBy { it.itemPosition }
        viewModelScope.launch {
            mScreenState.emit(tmp.toList())
        }
    }

    fun getNextItemPos(): Int {
        return ++lastItemPos
    }

    fun savePost(filesDir: File) {
        viewModelScope.launch {
            val newPost = CreatePostDto(
                authorId = prefs.data.map {
                    it[userSessionKey]?.toIntOrNull()
                }.firstOrNull() ?: 0,
                postTitle = mScreenState.value[0].value,
                postPic = mScreenState.value[2].value,
                creationDate = LocalDateTime.now().toString(),
                postGeo = mScreenState.value[3].value,
                content = screenState.value.map {
                    if(it.itemType == ItemType.ImageItem){

                        val file = File(filesDir, it.value)
                        val requestFile = RequestBody.create("image/jpeg".toMediaTypeOrNull(), file)

                        // MultipartBody.Part используется, чтобы отправить также название файла
                        val body = MultipartBody.Part.createFormData("picture", file.name, requestFile)

                        val savedFile = createPostUseCase.invoke(body)
                        PostItemDto(it.itemPosition, savedFile, it.itemType.typeName)
                    } else {
                        PostItemDto(it.itemPosition, it.value, it.itemType.typeName)
                    }
                }
            )
            createPostUseCase.invoke(newPost)
        }.invokeOnCompletion {
            navBack()
        }
    }
}

