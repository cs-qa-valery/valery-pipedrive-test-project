package com.spinkevich.domain.usecase

import com.spinkevich.domain.model.WordModel

interface StudyWordsUseCase {

    suspend fun getWords(): List<WordModel>

    suspend fun addWord(wordModel: WordModel)

    suspend fun deleteWord(wordModel: WordModel)
}