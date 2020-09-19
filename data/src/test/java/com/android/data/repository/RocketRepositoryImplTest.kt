package com.android.data.repository

import com.android.common_test.TestUtil
import com.android.data.repository.datasource.rocket.RocketDataSource
import com.android.domain.repository.RocketRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by hassanalizadeh on 19,September,2020
 */
@RunWith(JUnit4::class)
class RocketRepositoryImplTest {

    @Mock
    private lateinit var dataSource: RocketDataSource
    private lateinit var repository: RocketRepository


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = RocketRepositoryImpl(dataSource)
    }

    @Test
    fun `get rocket by id`() {
        //GIVEN
        doReturn(Single.just(TestUtil.rocketFromRemote()))
            .whenever(dataSource).rocket(anyString())

        //WHEN
        repository.rocket(TestUtil.rocketId)
            .test()
            .assertValue {
                it.name == TestUtil.rocketFromRemote().name
            }
            .assertComplete()

        //THEN
        verify(dataSource).rocket(TestUtil.rocketId)
    }

}