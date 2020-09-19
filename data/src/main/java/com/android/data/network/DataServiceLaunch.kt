package com.android.data.network

import com.android.data.entity.model.remote.LaunchList
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by hassanalizadeh on 19,September,2020
 */
interface DataServiceLaunch {
    @POST("launches/query")
    fun launches(@Body body: QueryBody): Single<LaunchList>
}