package com.jakelaurie.colormemory.ui

import android.support.annotation.CallSuper
import dagger.android.support.DaggerFragment
import dagger.android.support.HasSupportFragmentInjector

abstract class BaseFragment: DaggerFragment(), HasSupportFragmentInjector {

    @CallSuper
    override fun onResume() {
        super.onResume()
        getPresenter()?.resume()
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
        getPresenter()?.pause()
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.destroy()
    }

    protected abstract fun getPresenter(): BasePresenter?
}