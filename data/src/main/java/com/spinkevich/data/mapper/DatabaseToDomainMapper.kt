package com.spinkevich.data.mapper

import com.spinkevich.data.source.db.entity.WordSchema
import com.spinkevich.domain.model.WordModel

class DatabaseToDomainMapper {

    fun mapToDomain(wordSchema: WordSchema): WordModel {
        return WordModel(
            original = wordSchema.original,
            translation = wordSchema.translation,
            direction = wordSchema.direction
        )
    }

    fun mapToDatabaseSchema(wordModel: WordModel): WordSchema {
        return WordSchema(
            id = wordModel.hashCode(),
            original = wordModel.original,
            translation = wordModel.translation,
            direction = wordModel.direction
        )
    }
}