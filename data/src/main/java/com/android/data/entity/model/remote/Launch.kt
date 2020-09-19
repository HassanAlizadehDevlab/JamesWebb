package com.android.data.entity.model.remote

import com.google.gson.annotations.SerializedName

/**
 * Created by hassanalizadeh on 19,September,2020
 */
data class Launch(
    val id: String,
    val name: String,
    val success: Boolean,
    val rocket: String,
    @SerializedName("date_unix")
    val dateUnix: Long
)