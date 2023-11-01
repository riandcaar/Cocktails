package com.juanpoveda.cocktails.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.juanpoveda.cocktails.R
import com.juanpoveda.cocktails.databinding.FragmentSplashScreenBinding
import com.juanpoveda.cocktails.presentation.base.BaseFragment
import com.juanpoveda.cocktails.presentation.home.CocktailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

/**
 * This Fragment shows the initial splash screen animation (Lottie).
 */
@AndroidEntryPoint
class SplashScreenFragment : BaseFragment<FragmentSplashScreenBinding>() {

    private val viewModel: CocktailsViewModel by activityViewModels()

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashScreenBinding {
        return FragmentSplashScreenBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCocktailList()

        lifecycleScope.launchWhenCreated {
            goToCocktailListFragment()
        }
    }

    private suspend fun goToCocktailListFragment() {
        delay(4000)
        findNavController().navigate(R.id.action_SplashScreenFragment_to_CocktailListFragment)
    }
}
