package com.jakelaurie.colormemory.ui.game

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.jakelaurie.colormemory.R
import com.jakelaurie.colormemory.domain.model.GameCard
import com.jakelaurie.colormemory.ui.viewholder.ClickListener
import com.jakelaurie.colormemory.ui.viewholder.GameCardItemViewHolder

class GameAdapter: BaseAdapter() {
    val itemCount = 16
    var data: List<GameCard> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var clickListener: ClickListener? = null

    init {

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder: GameCardItemViewHolder?
        var view = convertView
        when (view == null) {
            true -> {
                view = View.inflate(parent.context, R.layout.item_game_card, parent)
                viewHolder = GameCardItemViewHolder(view)
                view.tag = viewHolder
            }

            false -> {
                viewHolder = view?.tag as? GameCardItemViewHolder
            }
        }

        viewHolder?.bind(getItem(position), position) { clickedView: View, clickedPosition: Int ->
            //Reload me --
            clickListener?.invoke(clickedView, clickedPosition)
        }

        return view!! //Either inflated or recycled
    }

    override fun getItem(position: Int): GameCard {
        return data[position]
    }

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount(): Int = data.size
}