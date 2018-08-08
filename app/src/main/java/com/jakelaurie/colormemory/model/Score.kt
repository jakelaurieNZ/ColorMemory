package com.jakelaurie.colormemory.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "score")
class Score(@PrimaryKey val id: String): Serializable {
    var playerName: String = ""
    var score: Int = 0
}

