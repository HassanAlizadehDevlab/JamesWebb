package com.android.presentation.common.navigator

import androidx.fragment.app.FragmentManager
import com.android.presentation.R
import com.android.presentation.common.extension.addFragment
import com.android.presentation.common.view.BaseActivityModule
import com.android.presentation.ui.launch.LaunchFragment
import com.android.presentation.ui.rocket.RocketFragment
import javax.inject.Inject
import javax.inject.Named

/**
 * Handle all navigation in here.
 *
 * @param fragmentManager is [MainActivity]'s fragmentManager.
 */
class Navigator @Inject constructor(
    @Named(BaseActivityModule.ACTIVITY_FRAGMENT_MANAGER)
    val fragmentManager: FragmentManager
) {

    fun showLaunches() {
        fragmentManager.addFragment(
            R.id.fragmentContainer,
            LaunchFragment.newInstance()
        )
    }

    fun showRocket(rocketId: String) {
        fragmentManager.addFragment(
            R.id.fragmentContainer,
            RocketFragment.newInstance(rocketId),
            RocketFragment::class.java.name
        )
    }

}