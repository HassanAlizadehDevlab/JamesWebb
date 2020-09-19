package com.android.presentation.ui.launch

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import com.android.domain.entity.LaunchObject
import com.android.domain.usecase.invoke
import com.android.domain.usecase.launch.GetLaunchesUseCase
import com.android.domain.usecase.launch.RefreshLaunchesUseCase
import com.android.presentation.adapter.ActionType
import com.android.presentation.adapter.BaseAction
import com.android.presentation.common.extension.map
import com.android.presentation.common.view.BaseViewModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by hassanalizadeh on 19,September,2020
 */
class LaunchViewModel @Inject constructor(
    private val getLaunchesUseCase: GetLaunchesUseCase,
    private val refreshLaunchesUseCase: RefreshLaunchesUseCase
) : BaseViewModel() {

    private val _launches: LiveData<List<LaunchObject>> =
        LiveDataReactiveStreams.fromPublisher(getLaunchesUseCase.invoke())
    val launches: LiveData<MutableList<LaunchObject>> = _launches.map { it.toMutableList() }

    val clickObservable = MutableLiveData<BaseAction>()
    val isRefreshing = MutableLiveData<Boolean>()


    fun refresh(successLaunches: Boolean? = null) {
        isRefreshing.value = true
        refreshLaunchesUseCase.invoke(successLaunches)
            .doOnEvent { isRefreshing.value = false }
            .onError()
            .subscribe({}, {})
            .track()
    }

    fun refreshWithSuccessLaunches() {
        refresh(true)
    }

    /**
     * @param actions contains all of the action we have for each ad items in view
     * */
    fun observeClicks(actions: Observable<BaseAction>) {
        actions.subscribe {
            when (it.getType()) {
                ActionType.LAUNCH -> {
                    clickObservable.value = it
                }
            }
        }.track()
    }

}