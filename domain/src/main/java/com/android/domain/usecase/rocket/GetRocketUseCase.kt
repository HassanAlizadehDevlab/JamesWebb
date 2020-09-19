package com.android.domain.usecase.rocket

import com.android.domain.entity.RocketObject
import com.android.domain.executor.transformer.STransformer
import com.android.domain.repository.RocketRepository
import com.android.domain.usecase.UseCaseSingle
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by hassanalizadeh on 19,September,2020
 */
class GetRocketUseCase @Inject constructor(
    private val repository: RocketRepository,
    private val transformer: STransformer<RocketObject>
) : UseCaseSingle<RocketObject, String>() {
    override fun execute(param: String): Single<RocketObject> {
        return repository.rocket(param)
            .compose(transformer)
    }
}