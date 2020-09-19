package com.android.data.entity.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by hassanalizadeh on 19,September,2020
 */
@Entity(tableName = "launch")
data class LaunchEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val success: Boolean,
    val rocket: String,
    val dateUnix: Long
)