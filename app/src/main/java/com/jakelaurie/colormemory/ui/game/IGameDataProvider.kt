package com.jakelaurie.colormemory.ui.game

import com.jakelaurie.colormemory.domain.model.GameCard

interface IGameDataProvider {
    fun getItems(): List<GameCard>
}