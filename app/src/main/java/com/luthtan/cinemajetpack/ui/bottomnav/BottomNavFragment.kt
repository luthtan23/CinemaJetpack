package com.luthtan.cinemajetpack.ui.bottomnav

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.ui.NavigationUI
import com.luthtan.cinemajetpack.R
import com.luthtan.cinemajetpack.databinding.BottomNavFragmentLayoutBinding
import com.luthtan.cinemajetpack.listener.BottomNavVisibilityListener

class BottomNavFragment : Fragment() {

    private val bottomNavSelectedItemIdKey = "BOTTOM_NAV_SELECTED_ITEM_ID_KEY"
    private var bottomNavSelectedItemId = R.id.home

    private var _binding: BottomNavFragmentLayoutBinding? = null
    private val binding get() = _binding!!

    private var bottomNavListener: BottomNavVisibilityListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomNavFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState?.let {
            bottomNavSelectedItemId =
                savedInstanceState.getInt(bottomNavSelectedItemIdKey, bottomNavSelectedItemId)
        }

        setupBottomNavigationBar()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(bottomNavSelectedItemIdKey, bottomNavSelectedItemId)
        super.onSaveInstanceState(outState)
    }

    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.movie,
            R.navigation.tvshow,
            R.navigation.favorite,
            R.navigation.user
        )

        binding.bottomNavView.selectedItemId = bottomNavSelectedItemId

        bottomNavListener?.bottomViewComponent(binding.bottomNavView)
        bottomNavListener?.toolbarBottomView(binding.bottomNavToolbar)

        val controller = binding.bottomNavView.setupWithNavController(
            fragmentManager = childFragmentManager,
            navGraphIds = navGraphIds,
            backButtonBehaviour = BackButtonBehaviour.POP_HOST_FRAGMENT,
            containerId = R.id.bottom_nav_container,
            firstItemId = R.id.home,
            intent = requireActivity().intent
        )

        controller.observe(viewLifecycleOwner, { navController ->
            NavigationUI.setupWithNavController(binding.bottomNavToolbar, navController)
            bottomNavSelectedItemId = navController.graph.id
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        bottomNavListener = context as BottomNavVisibilityListener
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}