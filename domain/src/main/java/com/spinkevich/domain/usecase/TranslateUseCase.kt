package com.spinkevich.domain.usecase

import com.spinkevich.domain.model.TranslationModel

interface TranslateUseCase {

    suspend fun translate(direction: String, text: String): TranslationModel
}