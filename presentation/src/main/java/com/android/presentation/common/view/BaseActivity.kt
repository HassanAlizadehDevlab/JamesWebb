package com.android.presentation.common.view

import android.widget.Toast
import androidx.annotation.StringRes
import com.android.presentation.common.extension.toast
import com.android.presentation.common.navigator.Navigator
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Base class for all activities.
 */
open class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    protected fun showMessage(@StringRes resourceId: Int) {
        showMessage(getString(resourceId))
    }

    protected fun showMessage(message: String) {
        toast(message)
    }


    /**
     * function to go back to previous fragment
     */
    protected fun oneStepBack() {
        if (supportFragmentManager.backStackEntryCount >= 2) {
            supportFragmentManager.popBackStack()
        } else doubleClickToExit()
    }

    // double back pressed function
    private var back_pressed: Long = 0

    private fun doubleClickToExit() {
        if (back_pressed + 2000 > System.currentTimeMillis()) finish() else
            toast("Click again to exit", Toast.LENGTH_SHORT)
        back_pressed = System.currentTimeMillis()
    }

}