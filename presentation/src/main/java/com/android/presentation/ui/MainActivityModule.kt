package com.android.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import com.android.presentation.common.di.ActivityScope
import com.android.presentation.common.di.FragmentScope
import com.android.presentation.common.view.BaseActivityModule
import com.android.presentation.ui.launch.LaunchFragment
import com.android.presentation.ui.launch.LaunchFragmentModule
import com.android.presentation.ui.rocket.RocketFragment
import com.android.presentation.ui.rocket.RocketFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by hassanalizadeh on 19,September,2020
 */
@Module(
    includes = [
        BaseActivityModule::class
    ]
)
abstract class MainActivityModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [LaunchFragmentModule::class])
    abstract fun launchFragmentInjector(): LaunchFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [RocketFragmentModule::class])
    abstract fun rocketFragmentInjector(): RocketFragment

    @Binds
    @ActivityScope
    abstract fun activity(mainActivity: MainActivity): AppCompatActivity

}