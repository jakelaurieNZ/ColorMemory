package com.jakelaurie.colormemory.di.module

import dagger.Module
import android.app.Activity
import android.content.Context
import dagger.Binds
import android.support.v7.app.AppCompatActivity
import com.jakelaurie.colormemory.di.scope.ActivityScope
import javax.inject.Named

@Module
abstract class BaseActivityModule {
    @Binds
    @ActivityScope
    internal abstract fun activity(appCompatActivity: AppCompatActivity): Activity

    @Binds
    @ActivityScope
    @Named("ActivityContext")
    internal abstract fun activityContext(activity: Activity): Context
}