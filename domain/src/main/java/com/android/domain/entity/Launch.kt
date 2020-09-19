package com.android.domain.entity

/**
 * Created by hassanalizadeh on 19,September,2020
 */
data class Launch(
    val id: String,
    val name: String,
    val success: Boolean,
    val rocket: String,
    val dateUnix: String
): DomainObject