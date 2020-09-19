package com.android.domain.usecase

import io.reactivex.Completable

/**
 * Created by hassanalizadeh on 25,February,2019
 */
abstract class UseCaseCompletable<P> {

    operator fun invoke(param: P?): Completable {
        return execute(param)
    }

    protected abstract fun execute(param: P?): Completable

}

operator fun UseCaseCompletable<Unit>.invoke(): Completable = this(Unit)