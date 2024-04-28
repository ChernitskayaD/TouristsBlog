package com.example.touristsblog.network

import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface DadataApiService {
    @Headers("Content-Type: application/json",
        "Authorization: Token 50e6bf880bc2569edcb120a906edd38eda2d23b8",
        "X-Secret: dbdf4df20de02040e6bfea8e7710cc19ed42f6f1")
    @POST("http://suggestions.dadata.ru/suggestions/api/4_1/rs/suggest/address")
    suspend fun getSuggests(@Body body: DadataBody): DadataResponse
}

data class DadataBody(
    @SerializedName("query") val query: String,
    @SerializedName("count") val count: Int,
)
data class DadataResponse(
    @SerializedName("suggestions") val suggestions: List<Suggestion>,
)
data class Suggestion(
    @SerializedName("suggestions") val value: String,
    @SerializedName("unrestricted_value") val unrestrictedValue: String,
    @SerializedName("data") val data: SuggestionData?,
)

data class SuggestionData(
    val city: String?,
    val street: String?
)