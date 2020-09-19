package com.android.presentation.common.view

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.android.presentation.common.extension.addFragment
import com.android.presentation.common.extension.detachFragment
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

}