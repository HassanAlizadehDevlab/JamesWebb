package com.android.presentation.ui.launch.adapter

import android.view.View
import androidx.core.content.ContextCompat
import com.android.domain.entity.LaunchObject
import com.android.presentation.R
import com.android.presentation.adapter.BaseViewHolder
import com.android.presentation.adapter.ViewTypeHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_launch.view.*

/**
 * Created by hassanalizadeh on 19,September,2020
 */
class LaunchViewHolder(
    override val containerView: View
) : BaseViewHolder<LaunchObject>(containerView), LayoutContainer {

    override fun getType(): Int = ViewTypeHolder.LAUNCH_VIEW

    override fun bind(data: LaunchObject?) {
        data ?: return

        // Set data to view
        if (data.success)
            containerView.icRocket.setColorFilter(
                ContextCompat.getColor(
                    containerView.context,
                    R.color.colorAccent
                )
            )
        else
            containerView.icRocket.setColorFilter(
                ContextCompat.getColor(
                    containerView.context,
                    R.color.colorFailed
                )
            )

        containerView.txtLaunchName.text = data.name
        containerView.txtDateTime.text = data.dateTime

        // Handle click listener
        containerView.root.setOnClickListener {
            mSubject.onNext(ViewLaunchAction(data.rocketId))
        }
    }
}