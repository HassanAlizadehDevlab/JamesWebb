package com.android.jameswebb

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins

/**
 * Application class.
 */
class James : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerJamesComponent.factory().create(this)


    override fun onCreate() {
        super.onCreate()
        initRxErrorHandler()
    }

    /**
     * RxJavaPlugins.setErrorHandler used for handle rx errors like network errors
     * */
    private fun initRxErrorHandler() {
        RxJavaPlugins.setErrorHandler {}
    }
}