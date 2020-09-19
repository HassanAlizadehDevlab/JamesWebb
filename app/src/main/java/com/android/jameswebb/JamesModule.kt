package com.android.jameswebb

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by hassanalizadeh on 19,September,2020
 */
@Module(includes = [AndroidInjectionModule::class])
abstract class JamesModule {

    @Binds
    @Singleton
    abstract fun application(application: James): Application

}