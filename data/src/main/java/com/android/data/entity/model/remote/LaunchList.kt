package com.android.data.entity.model.remote

import com.google.gson.annotations.SerializedName

/**
 * Created by hassanalizadeh on 19,September,2020
 */
data class LaunchList(
    @SerializedName("docs")
    val launches: List<Launch>
)