package com.jakelaurie.colormemory.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.jakelaurie.colormemory.model.Score

@Database(entities = [Score::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun scoreDao(): ScoreDAO
}