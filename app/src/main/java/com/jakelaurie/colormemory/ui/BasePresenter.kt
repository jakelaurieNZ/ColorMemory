package com.jakelaurie.colormemory.ui

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
    fun resume() {
        mIsPaused = false
    }

    @CallSuper
    fun pause() {
        mIsPaused = true
    }

    @CallSuper
    fun destroy() {
        mIsPaused = true
    }
}

interface IBasePresenter<V: BaseView> {
    fun setView(view: V)
}
