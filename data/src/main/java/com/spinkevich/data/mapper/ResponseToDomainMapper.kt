package com.spinkevich.data.mapper

import com.spinkevich.data.source.api.response.TranslateResponse
import com.spinkevich.domain.model.Translation
import com.spinkevich.domain.model.TranslationModel

class ResponseToDomainMapper {

    fun mapToDomain(response: TranslateResponse): TranslationModel {
        return TranslationModel(
            response.def?.map {
                Translation(it.tr!![0].text ?: "")
            } ?: emptyList()
        )
    }
}