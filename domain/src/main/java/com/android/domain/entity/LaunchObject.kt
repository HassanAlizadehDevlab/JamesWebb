package com.android.domain.entity

/**
 * Created by hassanalizadeh on 19,September,2020
 */
data class LaunchObject(
    val id: String,
    val name: String,
    val success: Boolean,
    val rocketId: String?,
    val dateTime: String
): DomainObject