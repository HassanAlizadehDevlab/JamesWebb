package com.android.data.network

import com.android.data.entity.model.remote.Rocket
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryName

/**
 * Created by hassanalizadeh on 19,September,2020
 */
interface DataServiceRocket {
    @GET("rockets/{ID}")
    fun rocket(@Path("ID") rocketId: String): Single<Rocket>
}