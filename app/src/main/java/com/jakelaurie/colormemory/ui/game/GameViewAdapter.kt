package com.jakelaurie.colormemory.ui.game

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakelaurie.colormemory.R
import com.jakelaurie.colormemory.domain.model.GameCard
import com.jakelaurie.colormemory.ui.viewholder.ClickListener
import com.jakelaurie.colormemory.ui.viewholder.GameCardItemViewHolder
import javax.inject.Inject

class GameViewAdapter @Inject constructor(dataProvider: IGameDataProvider) {
    private var data: List<GameCard> = emptyList()

    var clickListener: ClickListener? = null

    init {
        data = dataProvider.getItems()
    }

    fun getItemCount(): Int = data.size


    fun getView(position: Int, parent: ViewGroup): View {
        val viewHolder: GameCardItemViewHolder?
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_game_card, parent, false)

        view.id = position

        viewHolder = GameCardItemViewHolder(view)
        view.tag = viewHolder

        viewHolder.bind(data[position], position) { clickedView: View, clickedPosition: Int ->
            clickListener?.invoke(clickedView, clickedPosition)
        }

        return view
    }
}