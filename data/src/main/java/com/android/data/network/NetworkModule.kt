package com.android.data.network

import dagger.Module
import dagger.Provides
import dagger.Reusable

/**
 * Remote API provider.
 */
@Module
class NetworkModule {

    @Provides
    @Reusable
    fun launchesDataService(dataServiceFactory: DataServiceFactory): DataServiceLaunch =
        dataServiceFactory.create(DataServiceLaunch::class.java)

    @Provides
    @Reusable
    fun rocketDataService(dataServiceFactory: DataServiceFactory): DataServiceRocket =
        dataServiceFactory.create(DataServiceRocket::class.java)

}