package com.android.data

import com.android.data.entity.EntityModule
import dagger.Module

/**
 * Created by hassanalizadeh on 19,September,2020
 */
@Module(
    includes = [
        EntityModule::class
    ]
)
abstract class DataModule