package com.jakelaurie.colormemory.ui

import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseFragment: Fragment(), HasSupportFragmentInjector {

    @Inject lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

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

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = childFragmentInjector
}