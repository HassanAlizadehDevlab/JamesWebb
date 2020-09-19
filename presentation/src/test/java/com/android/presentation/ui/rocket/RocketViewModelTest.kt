package com.android.presentation.ui.rocket

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.common_test.TestUtil
import com.android.common_test.observe
import com.android.data.entity.mapper.map
import com.android.domain.entity.RocketObject
import com.android.domain.usecase.rocket.GetRocketUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito

/**
 * Created by hassanalizadeh on 20,September,2020
 */
@RunWith(JUnit4::class)
class RocketViewModelTest {


    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val getRocketUseCase: GetRocketUseCase = mock()
    private lateinit var viewModel: RocketViewModel


    @Before
    fun setup() {
        viewModel = RocketViewModel(getRocketUseCase)
    }

    @Test
    fun `get rocket by id`() {
        // GIVEN
        dataExistsForGettingRocket()
        val rocketObserver = mock<(RocketObject) -> Unit>()
        val loadingObserver = mock<(Boolean) -> Unit>()
        viewModel.rocket.observe(rocketObserver)
        viewModel.isRefreshing.observe(loadingObserver)

        // WHEN
        viewModel.rocket(TestUtil.rocketId)

        // THEN
        verify(loadingObserver).invoke(true)
        verify(rocketObserver).invoke(TestUtil.rocketFromRemote().map())
        verify(loadingObserver).invoke(false)
    }


    private fun dataExistsForGettingRocket() {
        Mockito.doReturn(Single.just(TestUtil.rocketFromRemote().map()))
            .whenever(getRocketUseCase).invoke(anyString())
    }

}