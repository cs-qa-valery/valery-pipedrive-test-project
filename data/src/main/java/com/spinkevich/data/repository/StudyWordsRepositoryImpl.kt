package com.spinkevich.data.repository

import com.spinkevich.data.mapper.DatabaseToDomainMapper
import com.spinkevich.data.source.db.dao.WordsDao
import com.spinkevich.domain.model.WordModel
import com.spinkevich.domain.repository.StudyWordsRepository

class StudyWordsRepositoryImpl(
    private val wordsDao: WordsDao,
    private val databaseMapper: DatabaseToDomainMapper
) : StudyWordsRepository {

    override suspend fun getWords(): List<WordModel> {
        return wordsDao.getWords()
            .map { databaseMapper.mapToDomain(it) }
    }

    override suspend fun addWord(wordModel: WordModel) {
        val wordSchema = databaseMapper.mapToDatabaseSchema(wordModel)
        wordsDao.insert(wordSchema)
    }

    override suspend fun deleteWord(wordModel: WordModel) {
        val wordSchema = databaseMapper.mapToDatabaseSchema(wordModel)
        wordsDao.delete(wordSchema)
    }
}