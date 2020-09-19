package com.android.presentation.ui.launch

import androidx.lifecycle.ViewModel
import com.android.presentation.common.di.FragmentScope
import com.android.presentation.common.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by hassanalizadeh on 19,September,2020
 */
@Module
abstract class LaunchViewModelModule {
    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(LaunchViewModel::class)
    abstract fun launchViewModel(launchViewModel: LaunchViewModel): ViewModel
}