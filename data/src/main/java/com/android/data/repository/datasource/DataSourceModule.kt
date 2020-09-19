package com.android.data.repository.datasource

import com.android.data.repository.datasource.launch.LaunchDataSource
import com.android.data.repository.datasource.launch.SmartLaunchDataSource
import com.android.data.repository.datasource.rocket.RocketDataSource
import com.android.data.repository.datasource.rocket.SmartRocketDataSource
import dagger.Binds
import dagger.Module
import dagger.Reusable

/**
 * Created by hassanalizadeh on 19,September,2020
 */
@Module
abstract class DataSourceModule {

    @Binds
    @Reusable
    abstract fun provideLaunchesDataSource(
        smartLaunchDataSource: SmartLaunchDataSource
    ): LaunchDataSource

    @Binds
    @Reusable
    abstract fun provideRocketDataSource(
        smartRocketDataSource: SmartRocketDataSource
    ): RocketDataSource

}