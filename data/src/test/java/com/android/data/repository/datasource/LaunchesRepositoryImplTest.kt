package com.android.data.repository.datasource

import com.android.common_test.TestUtil
import com.android.data.repository.datasource.launch.LaunchDataSource
import com.android.domain.repository.LaunchesRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by hassanalizadeh on 19,September,2020
 */
@RunWith(JUnit4::class)
class LaunchesRepositoryImplTest {

    @Mock
    private lateinit var dataSource: LaunchDataSource
    private lateinit var repository: LaunchesRepository


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = LaunchesRepositoryImpl(dataSource)
    }

    @Test
    fun `get launches`() {
        //GIVEN
        doReturn(Flowable.just(TestUtil.launchesFromDB()))
            .whenever(dataSource).launches()

        //WHEN
        repository.launches()
            .test()
            .assertValue {
                it[0].id == TestUtil.launchesFromRemote().launches[0].id
            }
            .assertComplete()

        //THEN
        verify(dataSource).launches()
    }

    @Test
    fun `load launches`() {
        //GIVEN
        val successLaunches = false
        doReturn(Completable.complete()).whenever(dataSource).loadLaunches(anyBoolean())

        //WHEN
        repository.loadLaunches(successLaunches)
            .test()
            .assertComplete()

        //THEN
        verify(dataSource).loadLaunches(successLaunches)
    }

}