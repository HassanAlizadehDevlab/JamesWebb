package com.android.data.repository.datasource.rocket

import com.android.data.entity.model.remote.Rocket
import com.android.data.extension.onError
import com.android.data.network.DataServiceRocket
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by hassanalizadeh on 19,September,2020
 */
open class SmartRocketDataSource @Inject constructor(
    private val service: DataServiceRocket
) : RocketDataSource {
    override fun rocket(id: String): Single<Rocket> {
        return service.rocket(id)
            .onError()
    }
}