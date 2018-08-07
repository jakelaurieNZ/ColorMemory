package com.jakelaurie.colormemory.ui.game

import com.jakelaurie.colormemory.domain.model.GameCard
import com.jakelaurie.colormemory.ui.BasePresenter
import com.jakelaurie.colormemory.util.first
import com.jakelaurie.colormemory.util.second
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Presenter for the Gamefragment
 * Contains all game logic
 */
class GamePresenter @Inject constructor(private val adapter: GameViewAdapter, dataProvider: IGameDataProvider):
        BasePresenter<GameContract.View>(), GameContract.Presenter {

    private val data: List<GameCard> = dataProvider.getItems()
    private val actionDelayTime = 1000L
    private var currentPoints = 0
    private var currentMatches = 0
    private lateinit var gameSubject: PublishSubject<Selection>
    private var pairSize = 2

    override fun resume() {
        super.resume()
        adapter.data = data
        getView()?.setAdapter(adapter)

        gameSubject = PublishSubject.create()

        subscribeToAdapterChanges()
        subscribeToGameLogic()
    }

    private fun subscribeToAdapterChanges() {
        //Handles selection
        adapter.clickSubject?.subscribe {
            //Set item is selected, notify adapter to change state
            if(data[it.position].selected) {
                return@subscribe
            }

            //2nd Observable so we can impose 'blocking' logic above
            gameSubject.onNext(it)
            setItemSelected(it.position, true)
            adapter.notifyItemChanged(it.viewId, it.position)
        }
    }

    private fun subscribeToGameLogic() {
        //Handles game logic
        gameSubject.buffer(pairSize)
                ?.delay(actionDelayTime, TimeUnit.MILLISECONDS)
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(AndroidSchedulers.mainThread())
                ?.subscribe {
                    if (checkMatch(it)) {
                        onMatchSuccess()
                        setPairSolved(it)
                    } else {
                        onMatchFailure()
                        setItemSelected(it.first.position, false)
                        setItemSelected(it.second.position, false)
                    }

                    adapter.notifyItemChanged(it.first.viewId, it.first.position)
                    adapter.notifyItemChanged(it.second.viewId, it.second.position)
                }
    }

    private fun setItemSelected(position: Int, selected: Boolean) {
        data[position].selected = selected
    }

    /**
     * Set both items solved
     * Doesn't notify
     */
    private fun setPairSolved(pair: List<Selection>) {
        data[pair.first.position].solved = true
        data[pair.second.position].solved = true
    }

    /**
     * Check both items from selection are from the same pair
     */
    private fun checkMatch(selections: List<Selection>): Boolean {
        return selections[0].pairId == selections[1].pairId
    }

    /**
     * Handle point increment
     * Handle game completion
     */
    private fun onMatchSuccess() {
        currentPoints += 2
        currentMatches ++

        if(currentMatches == data.size / pairSize) {
            getView()?.onGameCompleted(currentPoints)
        }
    }

    /**
     * Handle point decrement
     */
    private fun onMatchFailure() {
        currentPoints -= 1
    }
}

data class Selection(val viewId: Int, val pairId: Int, val position: Int)
