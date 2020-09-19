package com.android.data.entity.mapper

import com.android.data.entity.model.local.LaunchEntity
import com.android.domain.entity.LaunchObject

/**
 * Created by hassanalizadeh on 19,September,2020
 */
fun List<LaunchEntity>.map(): List<LaunchObject> = map { it.map() }
fun LaunchEntity.map(): LaunchObject = LaunchObject(
    id = id, name = name, success = success, rocket = rocket, dateUnix = dateUnix
)