package com.android.data.network

import com.android.data.entity.model.remote.LaunchList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by hassanalizadeh on 19,September,2020
 */
interface DataServiceRocket {
    @GET("rockets/{ID}")
    fun launches(@Query(value = "ID") rocketId: String): Single<LaunchList>
}