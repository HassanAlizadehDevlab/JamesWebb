package com.android.domain.usecase.launch

import com.android.common_test.TestUtil
import com.android.common_test.transformer.TestFTransformer
import com.android.data.entity.mapper.map
import com.android.domain.repository.LaunchesRepository
import com.android.domain.usecase.invoke
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
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
class GetLaunchesUseCaseTest {

    @Mock
    private lateinit var repository: LaunchesRepository
    private lateinit var useCase: GetLaunchesUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        useCase = GetLaunchesUseCase(repository, TestFTransformer())
    }


    @Test
    fun execute() {
        // GIVEN
        val expected = TestUtil.launchesFromRemote().launches[0].id
        Mockito.doReturn(Flowable.just(TestUtil.launchesFromRemote().launches.map().map()))
            .whenever(repository)
            .launches()

        // WHEN
        useCase.invoke()
            .test()
            .assertValue {
                it[0].id == expected
            }
            .assertComplete()

        // THEN
        Mockito.verify(repository).launches()
    }
}