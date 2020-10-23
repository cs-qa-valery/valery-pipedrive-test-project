package com.spinkevich.data.source.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.spinkevich.data.source.db.entity.WordSchema

@Dao
interface WordsDao {

    @Query("SELECT * FROM words")
    fun getWords(): List<WordSchema>

    @Insert(onConflict = IGNORE)
    fun insert(wordSchema: WordSchema)

    @Delete
    fun delete(wordSchema: WordSchema)
}