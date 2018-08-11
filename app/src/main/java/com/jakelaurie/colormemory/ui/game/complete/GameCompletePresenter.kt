package com.jakelaurie.colormemory.ui.game.complete

import com.jakelaurie.colormemory.data.database.ScoreDAO
import com.jakelaurie.colormemory.model.Score
import com.jakelaurie.colormemory.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.*
import java.util.*
import javax.inject.Inject

class GameCompletePresenter @Inject constructor(private val scoreDAO: ScoreDAO):
        BasePresenter<GameCompleteContract.View>(), GameCompleteContract.Presenter {

    var mPoints: Int = 0

    private var scoreObservable: DisposableObserver<List<Score>>? = null

    override fun resume() {
        super.resume()

        val observable = object: DisposableObserver<List<Score>>() {
            override fun onComplete() {
                //Do nothing
            }

            override fun onNext(it: List<Score>) {
                if(!isPaused()) {
                    calculateScore(mPoints, it)
                }
            }

            override fun onError(e: Throwable) {
                if(!isPaused()) {
                    calculateScore(mPoints, null)
                }
            }
        }

        scoreDAO.queryScores()
                .toObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observable)

        scoreObservable = observable
    }

    private fun calculateScore(points: Int, scores: List<Score>?) {
        getView()?.displayPoints(points, isHighScore(points, scores))
    }

    private fun isHighScore(points: Int, scores: List<Score>?): Boolean {
        //If our score is higher than the last score, is high
        return when(scores != null && scores.isNotEmpty() && scores.size >= 10) {
            true ->  {
                points > scores?.last()?.score?: 0
            }
            else -> true
        }
    }

    override fun setPoints(points: Int) {
        mPoints = points
    }

    override fun onNameEntered(value: String) {
        if(value.isNotEmpty()) {
            val score = Score(UUID.randomUUID().toString())
            score.playerName = value
            score.score = mPoints

            launch(CommonPool) {
                scoreDAO.addScore(score)
            }

            getView()?.dismiss()
            getView()?.onHighscoreAdded()
        } else {
            getView()?.onNameError()
        }
    }

    override fun destroy() {
        super.destroy()
        scoreObservable?.let {
            if(!it.isDisposed) {
                it.dispose()
            }
        }
    }
}