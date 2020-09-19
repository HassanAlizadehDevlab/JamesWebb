package com.android.presentation.ui.launch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.domain.entity.LaunchObject
import com.android.presentation.R
import com.android.presentation.adapter.BaseAction
import com.android.presentation.common.extension.linearLayout
import com.android.presentation.common.extension.observe
import com.android.presentation.common.extension.viewModelProvider
import com.android.presentation.common.utils.Utils
import com.android.presentation.common.view.BaseFragment
import com.android.presentation.common.viewmodel.ViewModelProviderFactory
import com.android.presentation.ui.launch.adapter.LaunchAdapter
import com.android.presentation.ui.launch.adapter.ViewLaunchAction
import kotlinx.android.synthetic.main.fragment_launches.*
import javax.inject.Inject

/**
 * Created by hassanalizadeh on 19,September,2020
 */
class LaunchFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var factory: ViewModelProviderFactory
    private lateinit var viewModel: LaunchViewModel
    private lateinit var adapter: LaunchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModelProvider(factory)
        adapter = LaunchAdapter { holder ->
            viewModel.observeClicks(holder.observe())
        }

        observe(viewModel.launches, ::observeLaunches)
        observe(viewModel.isRefreshing, ::observeRefreshing)
        observe(viewModel.clickObservable, ::observeActions)
        observe(viewModel.messageObservable, ::showMessage)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_launches, container, false)


    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)



        setupSwipeRefreshLayout()
        setupRecyclerView()
        setupAdapter()

        if (savedInstanceState == null)
            onRefresh()
    }

    override fun onRefresh() {
        viewModel.refresh()
    }

    private fun setupRecyclerView() {
        recyclerView?.linearLayout(
            context = activityContext,
            spacing = Utils.convertPxToDp(activityContext, 8f).toInt()
        )
    }

    private fun setupAdapter() {
        recyclerView?.adapter = adapter
    }

    private fun setupSwipeRefreshLayout() {
        loadingIndicator.setOnRefreshListener(this)
        loadingIndicator?.setColorSchemeColors(
            ContextCompat.getColor(activityContext, R.color.colorAccent)
        )
    }
    private fun observeLaunches(venues: MutableList<LaunchObject>) {
        adapter.addItems(venues)
    }

    private fun observeActions(actions: BaseAction) {
        when (actions) {
            is ViewLaunchAction -> {
                Toast.makeText(activityContext,  "Hello Rocket", Toast.LENGTH_SHORT).show()
                navigator.showRocket(actions.data)
            }
            else -> {
            }
        }
    }

    private fun observeRefreshing(status: Boolean) {
        if (status) {
            showLoading()
        } else {
            hideLoading()
        }
    }

    private fun hideLoading() {
        if (loadingIndicator.isRefreshing)
            loadingIndicator.post { loadingIndicator?.isRefreshing = false }
    }

    private fun showLoading() {
        if (!loadingIndicator.isRefreshing)
            loadingIndicator.post { loadingIndicator?.isRefreshing = true }
    }

    companion object {
        fun newInstance(): LaunchFragment = LaunchFragment()
    }

}