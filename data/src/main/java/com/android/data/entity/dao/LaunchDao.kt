package com.android.data.entity.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.data.entity.model.local.LaunchEntity
import io.reactivex.Flowable

/**
 * Created by hassanalizadeh on 19,September,2020
 */
@Dao
interface LaunchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(launches: List<LaunchEntity>)

    @Query("SELECT * FROM launch")
    fun selectAll(): Flowable<List<LaunchEntity>>

    @Query("DELETE FROM launch")
    fun deleteAll()

}