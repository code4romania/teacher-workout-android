package com.teacherworkout.features.home

import android.os.Bundle
import android.view.View
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.teacherworkout.commons.ui.base.BaseFragment
import com.teacherworkout.commons.ui.extensions.setupWithNavController
import com.teacherworkout.features.home.R
import com.teacherworkout.features.home.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    layoutId = R.layout.fragment_home
) {
    private val navGraphIds = listOf(
        R.navigation.themes_nav_graph
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            setupNavigation()
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        setupNavigation()
    }
    
    private fun setupNavigation() {
        val navController = viewBinding.bottomNavigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = childFragmentManager,
            containerId = R.id.nav_host_container,
            intent = requireCompatActivity().intent
        )

        navController.observe(
            viewLifecycleOwner,
            {
                val appBarConfiguration = AppBarConfiguration
                    .Builder(R.id.themes_fragment)
                    .build()

                setupActionBarWithNavController(requireCompatActivity(), it, appBarConfiguration)
            }
        )
    }
}
