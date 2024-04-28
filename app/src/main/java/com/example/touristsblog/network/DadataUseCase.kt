package com.example.touristsblog.network

import javax.inject.Inject

class DadataUseCase @Inject constructor(
    private val repository: DadataRepository
) {
    suspend operator fun invoke(dadataBody: DadataBody) = repository.getSuggests(dadataBody)
}