package com.android.data.repository.datasource.launch

import com.android.data.entity.dao.LaunchDao
import com.android.data.entity.mapper.map
import com.android.data.entity.model.local.LaunchEntity
import com.android.data.extension.onError
import com.android.data.network.DataServiceLaunch
import com.android.data.network.Query
import com.android.data.network.QueryBody
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by hassanalizadeh on 19,September,2020
 */
open class SmartLaunchDataSource @Inject constructor(
    private val service: DataServiceLaunch,
    private val launchDao: LaunchDao
) : LaunchDataSource {

    override fun launches(): Flowable<List<LaunchEntity>> {
        return launchDao.selectAll()
    }

    override fun loadLaunches(successLaunches: Boolean?): Completable {
        return getQueryParams(successLaunches)
            .flatMap { service.launches(it) }
            .flatMap { clearLaunches().toSingle { it } }
            .flatMapCompletable { insertLaunches(it.launches.map()) }
            .onError()
    }

    private fun insertLaunches(launches: List<LaunchEntity>?): Completable {
        if (launches.isNullOrEmpty()) return Completable.complete()
        return Completable.fromAction { launchDao.insert(launches) }
            .onError()
    }

    private fun clearLaunches(): Completable {
        return Completable.fromAction { launchDao.deleteAll() }
    }

    private fun getQueryParams(success: Boolean?): Single<QueryBody> {
        return Single.just(Query(success))
            .map { query ->
                return@map QueryBody(query)
            }.onError()
    }

}