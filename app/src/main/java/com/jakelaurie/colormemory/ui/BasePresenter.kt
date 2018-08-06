package com.jakelaurie.colormemory.ui

interface BasePresenter {
    fun resume()

    fun pause()

    fun destroy()

    fun setView(view: BaseView)
}