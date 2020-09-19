package com.android.presentation.ui.launch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.common_test.TestUtil
import com.android.common_test.observe
import com.android.data.entity.mapper.map
import com.android.domain.entity.DomainObject
import com.android.domain.entity.LaunchObject
import com.android.domain.entity.SuccessLaunchObject
import com.android.domain.usecase.invoke
import com.android.domain.usecase.launch.GetLaunchesUseCase
import com.android.domain.usecase.launch.RefreshLaunchesUseCase
import com.android.presentation.adapter.BaseAction
import com.android.presentation.ui.launch.adapter.SuccessLaunchAction
import com.android.presentation.ui.launch.adapter.ViewLaunchAction
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

/**
 * Created by hassanalizadeh on 20,September,2020
 */
@RunWith(JUnit4::class)
class LaunchViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val getLaunchesUseCase: GetLaunchesUseCase = mock()
    private val refreshLaunchesUseCase: RefreshLaunchesUseCase = mock()
    private lateinit var viewModel: LaunchViewModel


    // @Before
    fun createViewModel() {
        viewModel = LaunchViewModel(getLaunchesUseCase, refreshLaunchesUseCase)
    }

    @Test
    fun `get launches`() {
        // GIVEN
        dataExistsForGettingLaunches()
        dataExistsForLoadingLaunchesWithNullParameter()
        val launchesObserver = mock<(List<DomainObject>) -> Unit>()
        val loadingObserver = mock<(Boolean) -> Unit>()
        createViewModel()
        viewModel.launches.observe(launchesObserver)
        viewModel.isRefreshing.observe(loadingObserver)

        // WHEN
        viewModel.refresh()

        // THEN
        verify(loadingObserver).invoke(true)
        assert(viewModel.launches.value?.get(0) is SuccessLaunchObject)
        assert(viewModel.launches.value?.get(1) is LaunchObject)
        verify(loadingObserver).invoke(false)
    }

    @Test
    fun `click on launches`() {
        //GIVEN
        val action = ViewLaunchAction(TestUtil.rocketId)
        val clickObservable = PublishSubject.create<BaseAction>()
        val observer = mock<(BaseAction) -> Unit>()
        createViewModel()
        viewModel.clickObservable.observe(observer)
        viewModel.observeClicks(clickObservable)

        //WHEN
        clickObservable.onNext(action)

        //THEN
        verify(observer).invoke(action)
    }


    @Test
    fun `click on load success launches`() {
        //GIVEN
        dataExistsForLoadingLaunches()
        val action = SuccessLaunchAction(Unit)
        val clickObservable = PublishSubject.create<BaseAction>()
        val observer = mock<(BaseAction) -> Unit>()
        createViewModel()
        viewModel.clickObservable.observe(observer)
        viewModel.observeClicks(clickObservable)

        //WHEN
        clickObservable.onNext(action)

        //THEN
        verify(observer, never()).invoke(action)
        verify(refreshLaunchesUseCase).invoke(true)
    }

    private fun dataExistsForLoadingLaunches() {
        doReturn(Completable.complete())
            .whenever(refreshLaunchesUseCase).invoke(anyBoolean())
    }

    private fun dataExistsForLoadingLaunchesWithNullParameter() {
        doReturn(Completable.complete())
            .whenever(refreshLaunchesUseCase).invoke(null)
    }

    private fun dataExistsForGettingLaunches() {
        doReturn(Flowable.just(TestUtil.launchesFromDB().map()))
            .whenever(getLaunchesUseCase).invoke()
    }
}