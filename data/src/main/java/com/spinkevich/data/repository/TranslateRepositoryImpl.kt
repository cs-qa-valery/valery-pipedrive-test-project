package com.spinkevich.data.repository

import com.spinkevich.data.BuildConfig
import com.spinkevich.data.mapper.ResponseToDomainMapper
import com.spinkevich.data.source.api.service.TranslateService
import com.spinkevich.domain.model.TranslationModel
import com.spinkevich.domain.repository.TranslateRepository

class TranslateRepositoryImpl(
    private val service: TranslateService,
    private val domainMapper: ResponseToDomainMapper
) : TranslateRepository {

    override suspend fun translate(direction: String, text: String): TranslationModel {
        val response = service.translate(BuildConfig.API_KEY, direction, text)
        return domainMapper.mapToDomain(response)
    }
}