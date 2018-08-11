package com.jakelaurie.colormemory.base

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import com.jakelaurie.colormemory.data.database.Database
import org.junit.After

abstract class DBTest {
    private lateinit var _db: Database
    val db: Database get() = _db

    fun initDb() {
        _db = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                Database::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun closeDb() {
        if(::_db.isInitialized) {
            _db.close()
        }
    }
}