package com.android.jameswebb

import com.android.data.DataModule
import com.android.domain.DomainModule
import com.android.presentation.PresentationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Project dagger component.
 */
@Singleton
@Component(
    modules = [
        JamesModule::class,
        DataModule::class,
        DomainModule::class,
        PresentationModule::class
    ]
)
interface JamesComponent : AndroidInjector<James> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: James): JamesComponent
    }

}