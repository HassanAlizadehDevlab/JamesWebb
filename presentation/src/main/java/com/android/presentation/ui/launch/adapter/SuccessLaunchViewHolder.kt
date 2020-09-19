package com.android.presentation.ui.launch.adapter

import android.view.View
import com.android.presentation.adapter.BaseViewHolder
import com.android.presentation.adapter.ViewTypeHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_success_launches.view.*

/**
 * Created by hassanalizadeh on 19,September,2020
 */
class SuccessLaunchViewHolder(
    override val containerView: View
) : BaseViewHolder<Unit>(containerView), LayoutContainer {

    override fun getType(): Int = ViewTypeHolder.SUCCESS_LAUNCH_VIEW

    override fun bind(data: Unit?) {
        // Handle click listener
        containerView.btnSuccessLaunches.setOnClickListener {
            mSubject.onNext(SuccessLaunchAction(Unit))
        }
    }
}