package com.android.domain.usecase.rocket

import com.android.common_test.TestUtil
import com.android.common_test.transformer.TestSTransformer
import com.android.data.entity.mapper.map
import com.android.domain.repository.RocketRepository
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
class GetRocketUseCaseTest {

    @Mock
    private lateinit var repository: RocketRepository
    private lateinit var useCase: GetRocketUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        useCase = GetRocketUseCase(repository, TestSTransformer())
    }


    @Test
    fun execute() {
        // GIVEN
        Mockito.doReturn(Single.just(TestUtil.rocketFromRemote().map()))
            .whenever(repository)
            .rocket(anyString())

        // WHEN
        useCase.invoke(TestUtil.rocketId)
            .test()
            .assertValue {
                it.name == TestUtil.rocketFromRemote().name
            }
            .assertComplete()

        // THEN
        Mockito.verify(repository).rocket(TestUtil.rocketId)
    }

}