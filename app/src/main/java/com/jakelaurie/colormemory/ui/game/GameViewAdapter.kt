package com.jakelaurie.colormemory.ui.game

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakelaurie.colormemory.R
import com.jakelaurie.colormemory.domain.model.GameCard
import com.jakelaurie.colormemory.ui.viewholder.ClickListener
import com.jakelaurie.colormemory.ui.viewholder.GameCardItemViewHolder
import javax.inject.Inject

/**
 * Adapter specifically for GameView
 * Similar to RecyclerView.Adapter with [onCreateViewHolder] and [onBindViewHolder] idioms
 */
class GameViewAdapter @Inject constructor(dataProvider: IGameDataProvider) {
    private var data: List<GameCard> = mutableListOf()

    var clickListener: ClickListener? = null
    var datasetObserver: DatasetObserver? = null

    init {
        data = dataProvider.getItems()
    }

    fun getItemCount(): Int = data.size

    fun toggleItemSelected(id: Int, position: Int) {
        data[position].selected = !data[position].selected

        datasetObserver?.itemChanged(id, position)
    }

    fun onCreateViewHolder(parent: ViewGroup): GameCardItemViewHolder {
        val viewHolder: GameCardItemViewHolder?
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_game_card, parent, false)
        viewHolder = GameCardItemViewHolder(view)

        return viewHolder
    }

    fun onBindViewHolder(viewHolder: GameCardItemViewHolder, position: Int) {
        viewHolder.bind(data[position], position) { clickedView: View, index: Int ->
            clickListener?.invoke(clickedView, index)
        }
    }
}

interface DatasetObserver {
    fun itemChanged(id: Int, position: Int)
}
