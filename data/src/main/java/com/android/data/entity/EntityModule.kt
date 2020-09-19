package com.android.data.entity

import android.app.Application
import androidx.room.Room
import com.android.data.entity.dao.LaunchDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Entity provider module.
 */
@Module
class EntityModule {

    @Provides
    fun launchDao(db: JamesDatabase): LaunchDao = db.launchDao()

    @Provides
    @Singleton
    fun database(application: Application): JamesDatabase = Room.databaseBuilder(
        application.applicationContext,
        JamesDatabase::class.java,
        "james_webb_db"
    ).build()

}