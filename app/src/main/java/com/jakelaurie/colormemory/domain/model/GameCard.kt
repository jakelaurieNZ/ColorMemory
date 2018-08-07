package com.jakelaurie.colormemory.domain.model

import android.support.annotation.DrawableRes

data class GameCard(@DrawableRes val defaultDrawableRes: Int,
                    @DrawableRes val revealedDrawableRes: Int,
                    val pairId: Int,
                    var selected: Boolean = false)