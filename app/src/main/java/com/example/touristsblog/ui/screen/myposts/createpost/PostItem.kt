package com.example.touristsblog.ui.screen.myposts.createpost

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class PostItem(
    val itemPosition: Int,
) : Parcelable {
    data class TextItem(
        val text: String,
        val position: Int,
    ) : PostItem(position)

    data class TitleItem(
        val title: String,
        val position: Int,
    ) : PostItem(position)

    data class ImageItem(
        val imageUri: String,
        val position: Int,
    ) : PostItem(position)

    data class GeoItem(
        val text: String,
        val position: Int,
    ) : PostItem(position)
}
