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

    private var resources: Array<Int> = emptyArray()

    init {
        val resourceArray = context.resources.obtainTypedArray(drawableArray)

        resources = Array(resourceArray.length()) {
            resourceArray.getResourceId(it, -1)
        }

        resourceArray.recycle()
    }

    override fun getItems(): List<GameCard> {
        val items = mutableListOf<GameCard>()

        resources.forEachIndexed { index, it ->
            items.add(GameCard(R.drawable.card_bg, it, index))
            items.add(GameCard(R.drawable.card_bg, it, index))
        }

        return items.shuffled(randomProvider)
    }
}
