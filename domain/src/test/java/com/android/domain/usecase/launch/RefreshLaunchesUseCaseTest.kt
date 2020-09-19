package com.android.domain.usecase.launch

import com.android.common_test.transformer.TestCTransformer
import com.android.domain.repository.LaunchesRepository
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by hassanalizadeh on 19,September,2020
 */
@RunWith(JUnit4::class)
class RefreshLaunchesUseCaseTest {

    @Mock
    private lateinit var repository: LaunchesRepository
    private lateinit var useCase: RefreshLaunchesUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        useCase = RefreshLaunchesUseCase(repository, TestCTransformer())
    }


    @Test
    fun execute() {
        // GIVEN
        val successLaunches = false
        Mockito.doReturn(Completable.complete()).whenever(repository).loadLaunches(anyBoolean())

        // WHEN
        useCase.invoke(successLaunches)
            .test()
            .assertComplete()

        // THEN
        Mockito.verify(repository).loadLaunches(successLaunches)
    }
}