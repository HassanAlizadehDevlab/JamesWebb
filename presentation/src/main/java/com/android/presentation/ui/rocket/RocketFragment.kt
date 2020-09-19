package com.android.presentation.ui.rocket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.domain.entity.RocketObject
import com.android.presentation.R
import com.android.presentation.common.extension.observe
import com.android.presentation.common.extension.viewModelProvider
import com.android.presentation.common.extension.visibleOrGone
import com.android.presentation.common.view.BaseFragment
import com.android.presentation.common.viewmodel.ViewModelProviderFactory
import kotlinx.android.synthetic.main.fragment_rocket.*
import javax.inject.Inject

/**
 * Created by hassanalizadeh on 20,September,2020
 */
class RocketFragment : BaseFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory
    private lateinit var viewModel: RocketViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModelProvider(factory)

        observe(viewModel.rocket, ::observeRocket)
        observe(viewModel.isRefreshing, ::observeRefreshing)
        observe(viewModel.messageObservable, ::showMessage)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_rocket, container, false)


    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        arguments?.getString(ROCKET)?.let { rocketId ->
            viewModel.rocket(rocketId)
        }
    }

    private fun observeRocket(rocket: RocketObject) {
        txtName.text = rocket.name
        txtCountry.text = rocket.country
        txtDescription.text = rocket.description
    }

    private fun observeRefreshing(status: Boolean) {
        loadingIndicator.visibleOrGone(status)
    }


    companion object {
        private const val ROCKET = "rocket"
        fun newInstance(rocketId: String): RocketFragment {
            return RocketFragment().apply {
                arguments = Bundle().apply { putString(ROCKET, rocketId) }
            }
        }
    }

}