package com.example.touristsblog.ui.screen.viewpost

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.rememberNavController
import com.example.touristsblog.BaseViewModel
import com.example.touristsblog.ui.screen.myposts.createpost.ItemType
import com.example.touristsblog.ui.screen.myposts.createpost.PostItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewPostViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private var lastItemPos = 2

    private val mScreenState = MutableStateFlow(
        savedStateHandle.get<List<PostItem>>("screenstate") ?: listOf(
            PostItem(value = "Заголовок", itemPosition =  0, itemType = ItemType.TitleItem),
            PostItem(value = "Текст", itemPosition =  1, itemType = ItemType.TextItem),
            PostItem(value = "https://travelswm.com/wp-content/uploads/2018/02/Vecherom-Moskva.jpg", itemPosition = 2, itemType = ItemType.ImageItem),
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

    fun getNextItemPos(): Int {
        return lastItemPos++
    }

    fun savePost() {

    }

    fun goBack(){
        navBack()
    }
}