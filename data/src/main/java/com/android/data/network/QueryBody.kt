package com.android.data.network

/**
 * Created by hassanalizadeh on 19,September,2020
 */
data class QueryBody(
    val query: Query,
    val options: Options = Options()
)

data class Query(
    val success: Boolean = false
)

data class Options(
    val pagination: Boolean = false
)