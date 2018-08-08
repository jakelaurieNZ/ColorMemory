package com.jakelaurie.colormemory.ui.base

import android.content.Context
import android.support.annotation.CallSuper
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseDialogFragment: DialogFragment(), HasSupportFragmentInjector, BaseView {

    @Inject lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

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

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return childFragmentInjector
    }

    protected abstract fun getPresenter(): BasePresenter<out BaseView>?
}