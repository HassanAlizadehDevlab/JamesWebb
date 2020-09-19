package com.android.presentation.ui.launch.adapter

import com.android.presentation.adapter.ActionType
import com.android.presentation.adapter.BaseAction

/**
 * Created by hassanalizadeh on 19,September,2020
 */
data class SuccessLaunchAction(val data: Unit) : BaseAction {
    override fun getType(): ActionType = ActionType.SUCCESS_LAUNCH
}