package com.android.data.repository.datasource.rocket

import com.android.data.entity.model.remote.Rocket
import io.reactivex.Single

/**
 * Created by hassanalizadeh on 19,September,2020
 */
interface RocketDataSource {
    fun rocket(id: String): Single<Rocket>
}