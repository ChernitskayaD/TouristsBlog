package com.example.touristsblog.network

import javax.inject.Inject

class DadataRepositoryImpl @Inject constructor(
    private val service: DadataApiService
) : DadataRepository {
    override suspend fun getSuggests(body: DadataBody): DadataResponse =
        service.getSuggests(body)
}