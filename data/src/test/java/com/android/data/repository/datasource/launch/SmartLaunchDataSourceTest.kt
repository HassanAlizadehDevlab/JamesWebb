package com.android.data.repository.datasource.launch

import com.android.common.error.ErrorThrowable
import com.android.common_test.TestUtil
import com.android.data.entity.dao.LaunchDao
import com.android.data.network.DataServiceLaunch
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by hassanalizadeh on 19,September,2020
 */
@RunWith(JUnit4::class)
class SmartLaunchDataSourceTest {

    @Mock
    private lateinit var dataService: DataServiceLaunch

    @Mock
    private lateinit var launchDao: LaunchDao
    private lateinit var smartLaunchDataSource: SmartLaunchDataSource


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        smartLaunchDataSource = Mockito.spy(
            SmartLaunchDataSource(
                dataService,
                launchDao
            )
        )
    }

    @Test
    fun `get launches`() {
        // GIVEN
        Mockito.doReturn(Flowable.just(TestUtil.launchesFromDB()))
            .whenever(launchDao)
            .selectAll()

        // WHEN
        smartLaunchDataSource.launches()
            .test()
            .assertComplete()

        // THEN
        Mockito.verify(launchDao).selectAll()
    }

    @Test
    fun `load launches with result`() {
        // GIVEN
        val successLaunches = true
        Mockito.doReturn(Single.just(TestUtil.launchesFromRemote()))
            .whenever(dataService).launches(any())

        // WHEN
        smartLaunchDataSource.loadLaunches(successLaunches)
            .test()
            .assertComplete()

        // THEN
        Mockito.verify(dataService).launches(argThat { !this.query.success })
        Mockito.verify(launchDao).deleteAll()
        Mockito.verify(launchDao).insert(
            argThat {
                this[0].id == TestUtil.launchesFromRemote().launches[0].id
            }
        )
    }

    @Test
    fun `load success launches with result`() {
        // GIVEN
        val successLaunches = true
        Mockito.doReturn(Single.just(TestUtil.launchesFromRemote()))
            .whenever(dataService).launches(any())

        // WHEN
        smartLaunchDataSource.loadLaunches(successLaunches)
            .test()
            .assertComplete()

        // THEN
        Mockito.verify(dataService).launches(argThat { this.query.success })
        Mockito.verify(launchDao).deleteAll()
        Mockito.verify(launchDao).insert(
            argThat {
                this[0].id == TestUtil.launchesFromRemote().launches[0].id
            }
        )
    }

    @Test
    fun `load launches on error`() {
        // GIVEN
        val successLaunches = true
        Mockito.doReturn(Single.error<ErrorThrowable>(TestUtil.error()))
            .whenever(dataService).launches(any())

        // WHEN
        smartLaunchDataSource.loadLaunches(successLaunches)
            .test()
            .assertNotComplete()

        // THEN
        Mockito.verify(launchDao, Mockito.never()).insert(Mockito.anyList())
    }

}