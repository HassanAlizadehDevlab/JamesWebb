package com.android.domain.repository

import com.android.domain.entity.RocketObject
import io.reactivex.Single

/**
 * Created by hassanalizadeh on 19,September,2020
 */
interface RocketRepository {
    fun rocket(id: String): Single<RocketObject>
}