package com.example.touristsblog.ui.screen.myposts.createpost

sealed class PostItem(
    val itemPosition: Int,
) {
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
