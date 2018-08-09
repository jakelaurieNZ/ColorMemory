package com.jakelaurie.colormemory.di.module

import android.arch.persistence.room.Room
import com.jakelaurie.colormemory.ColorApplication
import com.jakelaurie.colormemory.data.database.Database
import com.jakelaurie.colormemory.data.database.ScoreDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideDatabase(application: ColorApplication): Database
            = Room.databaseBuilder(application, Database::class.java, "db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton fun provideScoreDAO(database: Database): ScoreDAO = database.scoreDao()
}