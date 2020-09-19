package com.android.domain.usecase.launch

import com.android.domain.entity.LaunchObject
import com.android.domain.executor.transformer.FTransformer
import com.android.domain.repository.LaunchesRepository
import com.android.domain.usecase.UseCaseFlowable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by hassanalizadeh on 19,September,2020
 */
class GetLaunchesUseCase @Inject constructor(
    private val repository: LaunchesRepository,
    private val transformer: FTransformer<List<LaunchObject>>
) : UseCaseFlowable<List<LaunchObject>, Unit>() {
    override fun execute(param: Unit): Flowable<List<LaunchObject>> {
        return repository.launches()
            .compose(transformer)
    }
}