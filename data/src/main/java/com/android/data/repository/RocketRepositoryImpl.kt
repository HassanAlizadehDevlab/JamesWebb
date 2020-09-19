package com.android.data.repository

import com.android.data.entity.mapper.map
import com.android.data.repository.datasource.rocket.RocketDataSource
import com.android.domain.entity.RocketObject
import com.android.domain.repository.RocketRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by hassanalizadeh on 19,September,2020
 */
class RocketRepositoryImpl @Inject constructor(
    private val dataSource: RocketDataSource
) : RocketRepository {
    override fun rocket(id: String): Single<RocketObject> {
        return dataSource.rocket(id).map { it.map() }
    }
}