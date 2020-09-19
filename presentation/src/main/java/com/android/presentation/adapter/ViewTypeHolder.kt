package com.android.presentation.adapter

import com.android.domain.entity.DomainObject
import com.android.domain.entity.LaunchObject
import com.android.presentation.R

/**
 * Created by hassanalizadeh on 26,February,2019
 */
object ViewTypeHolder {

    val LAUNCH_VIEW: Int = R.layout.adapter_launch

    fun getView(obj: DomainObject?): Int {
        if (obj == null) return 0
        return when (obj::class) {
            LaunchObject::class -> LAUNCH_VIEW

            else -> 0
        }
    }
}
