package com.jakelaurie.colormemory.ui.game

import android.os.Handler
import com.jakelaurie.colormemory.domain.model.GameCard
import com.jakelaurie.colormemory.ui.BasePresenter
import javax.inject.Inject

/**
 * Presenter for the Gamefragment
 * Contains all game logic
 */
class GamePresenter @Inject constructor(private val adapter: GameViewAdapter, dataProvider: IGameDataProvider):
        BasePresenter<GameContract.View>(), GameContract.Presenter {

    private val data: List<GameCard> = dataProvider.getItems()
    private val currentSelections: MutableList<Selection> = mutableListOf()
    private val actionDelayTime = 1000L
    private var currentPoints = 0
    private var currentMatches = 0

    override fun resume() {
        super.resume()
        adapter.data = data
        getView()?.setAdapter(adapter)
    }

    override fun viewSelected(viewId: Int, position: Int) {
        val selectedItem = data[position]
        if(selectedItem.selected) {
            return //can't de-select a selected item
        }

        when(currentSelections.size) {
            0 -> {
                currentSelections.add(0, Selection(viewId, selectedItem.pairId, position))
                setItemSelected(position, true)
                adapter.notifyItemChanged(viewId, position)
            }
            1 -> {
                currentSelections.add(1, Selection(viewId, selectedItem.pairId, position))
                setItemSelected(position, true)
                adapter.notifyItemChanged(viewId, position)

                if(checkMatch(currentSelections)) {
                    Handler().postDelayed({
                        onMatchSuccess()

                        setPairSolved(currentSelections[0].position, currentSelections[1].position)

                        currentSelections[0].let {
                            adapter.notifyItemChanged(it.viewId, it.position)
                        }

                        currentSelections[1].let {
                            adapter.notifyItemChanged(it.viewId, it.position)
                        }

                        currentSelections.clear()

                    }, actionDelayTime)
                } else {
                    //No match found
                    //De-select pair
                    //Minus one point
                    Handler().postDelayed({
                        onMatchFailure()

                        currentSelections[0].let {
                            setItemSelected(it.position, false)
                            adapter.notifyItemChanged(it.viewId, it.position)
                        }

                        currentSelections[1].let {
                            setItemSelected(it.position, false)
                            adapter.notifyItemChanged(it.viewId, it.position)
                        }

                        //Clear selection
                        currentSelections.clear()
                    }, actionDelayTime)
                }
            }
        }
    }

    private fun setItemSelected(position: Int, selected: Boolean) {
        data[position].selected = selected
    }

    private fun setPairSolved(position: Int, pairPosition: Int) {
        data[position].solved = true
        data[pairPosition].solved = true
    }

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

        //Completed game will always be data.size /2
        if(currentMatches == data.size / 2) {
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
