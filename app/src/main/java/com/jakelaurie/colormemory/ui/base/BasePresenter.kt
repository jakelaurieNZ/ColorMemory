package com.jakelaurie.colormemory.ui.base

import android.support.annotation.CallSuper

abstract class BasePresenter<V: BaseView>: IBasePresenter<V> {
    private var mView: V? = null
    private var mIsPaused: Boolean = false

    @CallSuper
    override fun setView(view: V) {
        mView = view
    }

    fun getView(): V? {
        return mView
    }

    @CallSuper
    open fun resume() {
        mIsPaused = false
    }

    @CallSuper
    open fun pause() {
        mIsPaused = true
    }

    @CallSuper
    open fun destroy() {
        mIsPaused = true
    }

    fun isPaused(): Boolean = mIsPaused
}

interface IBasePresenter<V: BaseView> {
    fun setView(view: V)
}
