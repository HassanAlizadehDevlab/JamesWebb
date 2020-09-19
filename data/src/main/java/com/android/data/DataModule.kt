package com.android.data

import com.android.data.entity.EntityModule
import com.android.data.network.NetworkModule
import dagger.Module

/**
 * Created by hassanalizadeh on 19,September,2020
 */
@Module(
    includes = [
        EntityModule::class,
        NetworkModule::class
    ]
)
abstract class DataModule