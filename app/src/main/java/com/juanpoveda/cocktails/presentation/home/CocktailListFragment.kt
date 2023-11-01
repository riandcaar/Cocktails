package com.juanpoveda.cocktails.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanpoveda.cocktails.databinding.FragmentCocktailListBinding
import com.juanpoveda.cocktails.presentation.base.BaseFragment
import com.juanpoveda.cocktails.presentation.home.adapter.CocktailAdapter
import com.juanpoveda.cocktails.presentation.model.UiCocktail
import dagger.hilt.android.AndroidEntryPoint

/**
 * This Fragment displays a list of cocktails
 */
@AndroidEntryPoint
class CocktailListFragment : BaseFragment<FragmentCocktailListBinding>() {

    private val viewModel: CocktailsViewModel by activityViewModels()
    private lateinit var adapter: CocktailAdapter

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCocktailListBinding {
        return FragmentCocktailListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        observeCocktailListUiState()
    }

    private fun observeCocktailListUiState() {
        viewModel.uiState.collectWhileResumed {
            when (it) {
                is CocktailsViewModel.CocktailListUiState.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is CocktailsViewModel.CocktailListUiState.Success -> {
                    binding.progressBar.isVisible = false
                    adapter.submitList(it.cocktails)
                }
                is CocktailsViewModel.CocktailListUiState.Error -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun setAdapter() {
        adapter = CocktailAdapter(this::onCocktailClicked).also {
            binding.cocktailsRv.adapter = it
            binding.cocktailsRv.layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    private fun onCocktailClicked(uiCocktail: UiCocktail, cocktailImageView: ImageView) {
        uiCocktail.thumb?.let {
            val extras = FragmentNavigatorExtras(
                cocktailImageView to it
            )
            val action = CocktailListFragmentDirections.actionCocktailListFragmentToCocktailDetailsFragment(uiCocktail)
            this.findNavController().navigate(action, extras)
        }
    }

}
