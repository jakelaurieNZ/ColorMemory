package com.jakelaurie.colormemory.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.jakelaurie.colormemory.model.Score
import io.reactivex.Flowable

@Dao
interface ScoreDAO {
    @Query("SELECT * FROM score")
    fun queryScores(): Flowable<List<Score>>
}