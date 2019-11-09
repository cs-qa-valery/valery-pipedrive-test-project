package com.spinkevich.domain.repository

import com.spinkevich.domain.model.WordModel

interface StudyWordsRepository {

    suspend fun getWords(): List<WordModel>

    suspend fun addWord(wordModel: WordModel)

    suspend fun deleteWord(wordModel: WordModel)
}