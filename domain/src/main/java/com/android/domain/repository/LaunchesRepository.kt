package com.android.domain.repository

import com.android.domain.entity.Launch
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by hassanalizadeh on 19,September,2020
 */
interface LaunchesRepository {
    fun launches(): Flowable<List<Launch>>
    fun loadLaunches(successLaunches: Boolean): Completable
}