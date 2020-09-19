package com.android.presentation.ui.rocket

import androidx.lifecycle.ViewModel
import com.android.presentation.common.di.FragmentScope
import com.android.presentation.common.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by hassanalizadeh on 20,September,2020
 */
@Module
abstract class RocketViewModelModule {
    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(RocketViewModel::class)
    abstract fun rocketViewModel(rocketViewModel: RocketViewModel): ViewModel
}