package com.jakelaurie.colormemory.ui.game.complete

import android.util.Log
import com.jakelaurie.colormemory.data.database.ScoreDAO
import com.jakelaurie.colormemory.model.Score
import com.jakelaurie.colormemory.ui.base.BasePresenter
import kotlinx.coroutines.experimental.*
import java.util.*
import javax.inject.Inject

class GameCompletePresenter @Inject constructor(val scoreDAO: ScoreDAO):
        BasePresenter<GameCompleteContract.View>(), GameCompleteContract.Presenter {

    var points: Int = 0

    override fun resume() {
        super.resume()
        getView()?.displayPoints(points, isHighScore(points))
        scoreDAO.queryScores().subscribe {
            Log.e("tt", "" + it.size + " SCORES")
        }
    }

    private fun isHighScore(points: Int): Boolean {
        return true
    }

    override fun onNameEntered(value: String) {
        if(value.isNotEmpty()) {
            //TODO: abstract this?
            val score = Score(UUID.randomUUID().toString())
            score.playerName = value
            score.score = points

            async(CommonPool) {
                scoreDAO.addScore(score)
            }

            getView()?.dismiss()
            getView()?.onHighscoreAdded()
        } else {
            getView()?.onNameError()
        }
    }
}