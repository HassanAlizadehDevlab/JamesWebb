package com.android.data.repository

import com.android.data.repository.datasource.launch.LaunchDataSource
import com.android.domain.entity.LaunchObject
import com.android.domain.repository.LaunchesRepository
import io.reactivex.Flowable
import javax.inject.Inject
import com.android.data.entity.mapper.map
import io.reactivex.Completable

/**
 * Created by hassanalizadeh on 19,September,2020
 */
class LaunchesRepositoryImpl @Inject constructor(
    private val dataSource: LaunchDataSource
): LaunchesRepository {

    override fun launches(): Flowable<List<LaunchObject>> {
        return dataSource.launches().map { it.map() }
    }

    override fun loadLaunches(successLaunches: Boolean): Completable {
        return dataSource.loadLaunches(successLaunches)
    }
}