package com.jakelaurie.colormemory.ui.game

import android.content.Context
import android.support.annotation.ArrayRes
import android.support.annotation.DrawableRes
import com.jakelaurie.colormemory.R
import com.jakelaurie.colormemory.domain.model.GameCard
import java.util.*
import javax.inject.Inject

class ResourceGameDataProvider @Inject constructor(
        context: Context,
        @ArrayRes drawableArray: Int,
        private val randomProvider: Random): IGameDataProvider {

    private var items: MutableList<GameCard> = mutableListOf()

    init {
        context.resources.getIntArray(drawableArray).forEachIndexed { index, it ->
            val item = GameCard(defaultDrawableRes, it, index)
            items.add(item)
            items.add(item)
        }

        shuffle()
    }

    fun shuffle() {
        items.shuffle(randomProvider)
    }

    override fun getItems(): List<GameCard> = items

    fun getItemCount() = items.size

    companion object {
        @DrawableRes private val defaultDrawableRes = R.drawable.card_bg
    }
}