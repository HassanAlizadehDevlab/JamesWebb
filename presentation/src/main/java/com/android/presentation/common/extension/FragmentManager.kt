package com.android.presentation.common.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Created by hassanalizadeh on 28,August,2020
 */
fun FragmentManager.addFragment(
    containerViewId: Int,
    fragment: Fragment,
    tag: String
) {
    val fragmentPopped: Boolean = this.popBackStackImmediate(tag, 0)
    if (fragmentPopped) {
        // fragment is pop from backStack
    } else {
        this.beginTransaction()
            .add(containerViewId, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }
}