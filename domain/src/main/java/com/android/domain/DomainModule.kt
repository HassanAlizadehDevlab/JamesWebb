package com.android.domain

import com.android.domain.entity.LaunchObject
import com.android.domain.entity.RocketObject
import com.android.domain.executor.transformer.*
import dagger.Binds
import dagger.Module

/**
 * Created by hassanalizadeh on 19,September,2020
 */
@Module
abstract class DomainModule {
    @Binds
    abstract fun completableTransformer(
        transformer: AsyncCTransformer
    ): CTransformer

    @Binds
    abstract fun launchesTransformer(
        transformer: AsyncFTransformer<List<LaunchObject>>
    ): FTransformer<List<LaunchObject>>

    @Binds
    abstract fun rocketTransformer(
        transformer: AsyncSTransformer<RocketObject>
    ): STransformer<RocketObject>
}