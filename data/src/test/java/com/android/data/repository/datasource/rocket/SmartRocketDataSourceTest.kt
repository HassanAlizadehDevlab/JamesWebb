package com.android.data.repository.datasource.rocket

import com.android.common_test.TestUtil
import com.android.data.network.DataServiceRocket
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by hassanalizadeh on 19,September,2020
 */
@RunWith(JUnit4::class)
class SmartRocketDataSourceTest {

    @Mock
    private lateinit var dataService: DataServiceRocket

    private lateinit var smartRocketDataSource: SmartRocketDataSource


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        smartRocketDataSource = Mockito.spy(
            SmartRocketDataSource(
                dataService
            )
        )
    }


    @Test
    fun `get rocket by id`() {
        // GIVEN
        Mockito.doReturn(Single.just(TestUtil.rocketFromRemote()))
            .whenever(dataService)
            .rocket(anyString())

        // WHEN
        smartRocketDataSource.rocket(TestUtil.rocketId)
            .test()
            .assertComplete()

        // THEN
        Mockito.verify(dataService).rocket(TestUtil.rocketId)
    }
}