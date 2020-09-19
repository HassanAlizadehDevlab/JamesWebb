package com.android.presentation.ui.launch.adapter

import android.view.ViewGroup
import com.android.domain.entity.LaunchObject
import com.android.presentation.adapter.BaseRecyclerAdapter
import com.android.presentation.adapter.BaseViewHolder
import com.android.presentation.adapter.EmptyViewHolder
import com.android.presentation.adapter.ViewTypeHolder
import com.android.presentation.common.extension.inflate

/**
 * Created by hassanalizadeh on 19,September,2020
 */
class LaunchAdapter(
    private val listener: (BaseViewHolder<*>) -> Unit
) : BaseRecyclerAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = parent.inflate(viewType)
        val holder = when (viewType) {
            ViewTypeHolder.LAUNCH_VIEW -> LaunchViewHolder(view)
            else -> EmptyViewHolder(view)
        }
        listener(holder)
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val data = mItems[position]
        when (holder.getType()) {
            ViewTypeHolder.LAUNCH_VIEW -> (holder as LaunchViewHolder).bind(data as? LaunchObject)
        }
    }

}