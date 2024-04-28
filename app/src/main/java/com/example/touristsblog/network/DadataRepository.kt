package com.example.touristsblog.network

import retrofit2.http.Body

interface DadataRepository {
    suspend fun getSuggests(@Body body: DadataBody): DadataResponse
}