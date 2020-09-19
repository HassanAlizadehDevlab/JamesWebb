package com.android.data.entity

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.data.entity.dao.LaunchDao
import com.android.data.entity.model.local.LaunchEntity


/**
 * The James Webb's Database.
 */
@Database(
    entities = [
        LaunchEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class JamesDatabase : RoomDatabase() {

    abstract fun launchDao(): LaunchDao

}