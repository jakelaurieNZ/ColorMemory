package com.jakelaurie.colormemory.di.module

import android.support.v7.app.AppCompatActivity
import com.jakelaurie.colormemory.di.scope.ActivityScope
import com.jakelaurie.colormemory.ui.highscore.HighscoreActivity
import dagger.Binds
import dagger.Module

@Module(includes = [BaseActivityModule::class])
abstract class HighscoreActivityModule {
    @Binds
    @ActivityScope
    abstract fun appCompatActivity(highscoreActivity: HighscoreActivity): AppCompatActivity
}