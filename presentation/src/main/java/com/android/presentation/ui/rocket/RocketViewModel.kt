package com.android.presentation.ui.rocket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.domain.entity.RocketObject
import com.android.domain.usecase.rocket.GetRocketUseCase
import com.android.presentation.common.extension.map
import com.android.presentation.common.view.BaseViewModel
import javax.inject.Inject

/**
 * Created by hassanalizadeh on 20,September,2020
 */
class RocketViewModel @Inject constructor(
    private val getRocketUseCase: GetRocketUseCase
) : BaseViewModel() {

    private val _rocket = MutableLiveData<RocketObject>()
    val rocket : LiveData<RocketObject> = _rocket.map { it }
    val isRefreshing = MutableLiveData<Boolean>()


    fun rocket(rocketId: String) {
        isRefreshing.value = true
        getRocketUseCase.invoke(rocketId)
            .doOnEvent { _, _ -> isRefreshing.value = false }
            .onError()
            .subscribe({
                _rocket.value = it
            }, {})
            .track()
    }

}