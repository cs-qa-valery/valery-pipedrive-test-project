package com.spinkevich.data.source.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class WordSchema(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "original")
    val original: String,
    @ColumnInfo(name = "translation")
    val translation: String,
    @ColumnInfo(name = "direction")
    val direction: String
)