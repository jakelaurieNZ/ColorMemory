package com.jakelaurie.colormemory.di.module

import android.support.v7.app.AppCompatActivity
import com.jakelaurie.colormemory.di.scope.ActivityScope
import com.jakelaurie.colormemory.ui.game.GameActivity
import dagger.Binds
import dagger.Module

@Module(includes = [BaseActivityModule::class])
abstract class MainActivityModule {
    @Binds
    @ActivityScope
    abstract fun appCompatActivity(gameActivity: GameActivity): AppCompatActivity
}