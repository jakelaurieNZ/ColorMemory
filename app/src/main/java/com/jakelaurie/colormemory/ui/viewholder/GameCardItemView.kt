package com.jakelaurie.colormemory.ui.viewholder

import android.view.View
import com.bumptech.glide.Glide
import com.jakelaurie.colormemory.domain.model.GameCard
import kotlinx.android.synthetic.main.item_game_card.view.*

typealias ClickListener = ((View, Int) -> Unit)

class GameCardItemViewHolder(private val itemView: View) {
    fun bind(item: GameCard, position: Int, clickListener: ClickListener?) {
        Glide.with(itemView)
                .load(item.defaultDrawableRes)
                .into(itemView.gameCardImageView)

        itemView.setOnClickListener {
            clickListener?.invoke(itemView, position)
        }
    }
}