package com.spinkevich.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.spinkevich.data.source.db.dao.WordsDao
import com.spinkevich.data.source.db.entity.WordSchema

@Database(entities = [WordSchema::class], version = 1)
abstract class WordsDatabase : RoomDatabase() {

    abstract fun wordsDao(): WordsDao
}