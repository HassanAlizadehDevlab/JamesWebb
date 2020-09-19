package com.android.data.entity.mapper

import com.android.data.entity.model.remote.Rocket
import com.android.domain.entity.RocketObject

/**
 * Created by hassanalizadeh on 19,September,2020
 */
fun List<Rocket>.map(): List<RocketObject> = map { it.map() }

fun Rocket.map(): RocketObject = RocketObject(
    name = name, country = country, description = description
)