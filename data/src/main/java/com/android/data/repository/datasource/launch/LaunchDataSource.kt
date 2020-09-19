package com.android.data.repository.datasource.launch

import com.android.data.entity.model.local.LaunchEntity
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by hassanalizadeh on 19,September,2020
 */
interface LaunchDataSource {
    fun launches(): Flowable<List<LaunchEntity>>
    fun loadLaunches(successLaunches: Boolean = false): Completable
}