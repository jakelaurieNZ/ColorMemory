package com.jakelaurie.colormemory.di.module

import android.content.Context
import android.support.annotation.ArrayRes
import com.jakelaurie.colormemory.R
import com.jakelaurie.colormemory.ui.game.GameContract
import com.jakelaurie.colormemory.ui.game.GamePresenter
import com.jakelaurie.colormemory.ui.game.IGameDataProvider
import com.jakelaurie.colormemory.ui.game.ResourceGameDataProvider
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Named

@Module
class GameModule {
    @Provides
    fun providesGameDataProvider(
            @Named("ActivityContext") context: Context,
            @Named("CardsResource") @ArrayRes drawableArray: Int,
            random: Random): IGameDataProvider {
        //Provide specifc ResourceGameDataProvider concrete impl
        return ResourceGameDataProvider(context, drawableArray, random)
    }

    @Provides
    fun provideRandom(): Random {
        return Random()
    }

    @Provides
    @Named("CardsResource")
    fun provideCardsArray(): Int {
        return R.array.card_images_array
    }

    @Provides
    fun providesGamePresenter(gamePresenter: GamePresenter): GameContract.Presenter {
        return gamePresenter
    }
}