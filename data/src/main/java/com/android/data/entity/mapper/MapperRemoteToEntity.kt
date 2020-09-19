package com.android.data.entity.mapper

import android.annotation.SuppressLint
import com.android.data.entity.model.local.LaunchEntity
import com.android.data.entity.model.remote.Launch
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by hassanalizadeh on 19,September,2020
 */
fun List<Launch>.map(): List<LaunchEntity> = map { it.map() }

fun Launch.map(): LaunchEntity = LaunchEntity(
    id = id,
    name = name,
    success = success,
    rocket = rocketId,
    dateUnix = DateUtil.formatDate(dateUnix)
)

object DateUtil {
    @SuppressLint("SimpleDateFormat")
    private val dt = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    fun formatDate(unix: Long): String {
        return dt.format(Date(unix * 1000))
    }
}