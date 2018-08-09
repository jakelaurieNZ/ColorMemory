package com.jakelaurie.colormemory.ui.highscore

import com.jakelaurie.colormemory.data.database.ScoreDAO
import com.jakelaurie.colormemory.model.Score
import com.jakelaurie.colormemory.ui.base.BasePresenter
import com.jakelaurie.colormemory.ui.highscore.list.HighscoreAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HighscorePresenter @Inject constructor(private val adapter: HighscoreAdapter, private val scoreDao: ScoreDAO)
    : BasePresenter<HighscoreContract.View>(), HighscoreContract.Presenter {

    private var scoreObservable: DisposableObserver<List<Score>>? = null

    override fun resume() {
        super.resume()
        getView()?.setAdapter(adapter)

        val scoreObservable = object: DisposableObserver<List<Score>>() {
            override fun onComplete() {
                //Not called as the source is Flowable
            }

            override fun onNext(it: List<Score>) {
                if(!isPaused()) {
                    adapter.data = it
                }
            }

            override fun onError(e: Throwable) {
                //Do-nothing
            }
        }

        scoreDao.queryScores()
                .toObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(scoreObservable)

        this.scoreObservable = scoreObservable
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