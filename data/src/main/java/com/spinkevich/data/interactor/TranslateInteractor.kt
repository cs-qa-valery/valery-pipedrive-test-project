package com.spinkevich.data.interactor

import com.spinkevich.domain.model.TranslationModel
import com.spinkevich.domain.repository.TranslateRepository
import com.spinkevich.domain.usecase.TranslateUseCase

class TranslateInteractor(
    private val repository: TranslateRepository
) : TranslateUseCase {

    override suspend fun translate(direction: String, text: String): TranslationModel {
        return repository.translate(direction, text)
    }
}