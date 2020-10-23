package com.spinkevich.data.interactor

import com.spinkevich.domain.model.WordModel
import com.spinkevich.domain.repository.StudyWordsRepository
import com.spinkevich.domain.usecase.StudyWordsUseCase

class StudyWordsInteractor(
    private val studyWordsRepository: StudyWordsRepository
) : StudyWordsUseCase {

    override suspend fun getWords(): List<WordModel> {
        return studyWordsRepository.getWords()
    }

    override suspend fun addWord(wordModel: WordModel) {
        studyWordsRepository.addWord(wordModel)
    }

    override suspend fun deleteWord(wordModel: WordModel) {
        studyWordsRepository.deleteWord(wordModel)
    }
}