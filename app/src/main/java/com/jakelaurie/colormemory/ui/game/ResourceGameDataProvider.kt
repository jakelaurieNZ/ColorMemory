package com.jakelaurie.colormemory.ui.game

import android.content.Context
import android.support.annotation.ArrayRes
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
        val resourceArray = context.resources.obtainTypedArray(drawableArray)
        for (i in 0..(resourceArray.length() -1)) {
            val item = GameCard(R.drawable.card_bg, resourceArray.getResourceId(i, -1), i) //Id can be zero
            items.add(item)
            items.add(item)
        }

        resourceArray.recycle()
        shuffle()
    }

    fun shuffle() {
        items.shuffle(randomProvider)
    }

    override fun getItems(): List<GameCard> = items

    fun getItemCount() = items.size
}