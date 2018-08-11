package com.jakelaurie.colormemory.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.jakelaurie.colormemory.ColorApplication
import com.jakelaurie.colormemory.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.HasSupportFragmentInjector

class FragmentTestActivity : AppCompatActivity(), HasSupportFragmentInjector {
    private lateinit var injector: AndroidInjector<Fragment>

    override fun supportFragmentInjector() = injector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val application = application as ColorApplication
        val testAppComponent = DaggerAppComponent.builder().create(application)
        testAppComponent.inject(application)
    }

    fun startFragment(fragment: Fragment, injector: AndroidInjector<Fragment>) {
        this.injector = injector
        supportFragmentManager.beginTransaction()
                .add(android.R.id.content, fragment, fragment.javaClass.simpleName)
                .commit()
    }

    inline fun <reified T : Fragment> startFragment(fragment: T, crossinline injector: (T) -> Unit) {
        startFragment(fragment, AndroidInjector { if (it is T) injector(it) })
    }
}