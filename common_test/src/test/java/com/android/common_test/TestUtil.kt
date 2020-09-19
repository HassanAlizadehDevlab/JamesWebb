package com.android.common_test

import com.android.common.error.ErrorCode
import com.android.common.error.ErrorThrowable
import com.android.data.entity.mapper.map
import com.android.data.entity.model.local.LaunchEntity
import com.android.data.entity.model.remote.LaunchList
import com.android.data.entity.model.remote.Rocket
import com.google.gson.Gson

/**
 * Created by hassanalizadeh on 19,September,2020
 */
object TestUtil {

    val rocketId = "      "

    fun launchesFromDB(): List<LaunchEntity> {
        return launchesFromRemote().launches.map()
    }

    fun rocketFromRemote(): Rocket {
        return Gson().fromJson(parseJson("rocket.json"), Rocket::class.java)
    }

    fun launchesFromRemote(): LaunchList {
        return Gson().fromJson(parseJson("launches.json"), LaunchList::class.java)
    }

    private fun parseJson(fileName: String): String =
        javaClass.classLoader?.getResourceAsStream("json/$fileName")
            ?.bufferedReader().use { it?.readText().orEmpty() }

    fun error(): ErrorThrowable = ErrorThrowable(ErrorCode.ERROR_HAPPENED)

}