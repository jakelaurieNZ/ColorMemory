package com.jakelaurie.colormemory.util

import com.jakelaurie.colormemory.model.Score

class ScoresTestUtil {
    companion object {
        public fun createScores(count: Int, value: Int): List<Score> {
            val list = mutableListOf<Score>()
            for (i in 0 until count) {
                val score = Score(i.toString())
                score.playerName = ""
                score.score = value
                list.add(score)
            }

            return list
        }
    }
}
