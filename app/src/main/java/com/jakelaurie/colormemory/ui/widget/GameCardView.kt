package com.jakelaurie.colormemory.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.jakelaurie.colormemory.domain.model.GameCard
import kotlinx.android.synthetic.main.item_game_card.view.*

typealias ClickListener = ((View, Int) -> Unit)

class GameCardView @JvmOverloads constructor(context: Context,
                                             attributeSet: AttributeSet? = null,
                                             defStyleAttr: Int = 0, defStyleRes: Int = 0):
        FrameLayout(context, attributeSet, defStyleAttr, defStyleRes) {
    init {

    }

    fun bind(item: GameCard, position: Int, clickListener: ClickListener?) {
        Glide.with(this)
                .load(item.defaultDrawableRes)
                .into(gameCardImageView)

        setOnClickListener {
            clickListener?.invoke(this, position)
        }
    }
}