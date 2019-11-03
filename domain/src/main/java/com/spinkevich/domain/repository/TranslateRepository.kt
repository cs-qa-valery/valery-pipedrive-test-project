package com.spinkevich.domain.repository

import com.spinkevich.domain.model.TranslationModel

interface TranslateRepository {

    suspend fun translate(direction: String, text: String): TranslationModel
}