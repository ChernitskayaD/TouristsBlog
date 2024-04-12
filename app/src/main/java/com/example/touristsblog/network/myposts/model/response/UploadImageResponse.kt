package com.example.touristsblog.network.myposts.model.response

import com.google.gson.annotations.SerializedName

data class UploadImageResponse(
    @SerializedName("imageName")
    val imageName: String
)
