package com.android.domain.usecase.launch

import com.android.domain.executor.transformer.CTransformer
import com.android.domain.repository.LaunchesRepository
import com.android.domain.usecase.UseCaseCompletable
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by hassanalizadeh on 19,September,2020
 */
class RefreshLaunchesUseCase @Inject constructor(
    private val repository: LaunchesRepository,
    private val transformer: CTransformer
) : UseCaseCompletable<Boolean>() {
    override fun execute(param: Boolean): Completable {
        return repository.loadLaunches(param)
            .compose(transformer)
    }
}