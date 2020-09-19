package com.android.data.entity.mapper

import com.android.data.entity.model.local.LaunchEntity
import com.android.data.entity.model.remote.Launch

/**
 * Created by hassanalizadeh on 19,September,2020
 */
fun List<Launch>.map(): List<LaunchEntity> = map { it.map() }

fun Launch.map(): LaunchEntity = LaunchEntity(
    id = id, name = name, success = success, rocket = rocket, dateUnix = dateUnix
)