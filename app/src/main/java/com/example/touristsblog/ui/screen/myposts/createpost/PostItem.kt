package com.example.touristsblog.ui.screen.myposts.createpost

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostItem(
    val itemPosition: Int,
    val value: String,
    val itemType: ItemType
) : Parcelable

enum class ItemType(val typeName: String) {
    TextItem("text"),
    TitleItem("title"),
    ImageItem("image"),
    GeoItem("geo")
}
