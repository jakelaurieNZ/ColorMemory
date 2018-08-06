package com.jakelaurie.colormemory.di.scope

import javax.inject.Scope

/**
 * Custom scope to 'specify' lifespan to same as activity
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope
