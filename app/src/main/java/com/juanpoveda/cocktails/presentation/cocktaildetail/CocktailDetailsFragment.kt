package com.juanpoveda.cocktails.presentation.cocktaildetail

import android.app.Dialog
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.juanpoveda.cocktails.R
import com.juanpoveda.cocktails.databinding.FragmentCocktailDetailsBinding
import com.juanpoveda.cocktails.presentation.base.BaseFragment
import com.juanpoveda.cocktails.presentation.cocktaildetail.adapter.IngredientAdapter
import com.juanpoveda.cocktails.presentation.model.UiIngredient
import com.juanpoveda.cocktails.presentation.model.UiIngredientMeasure
import dagger.hilt.android.AndroidEntryPoint

/**
 * This Fragment displays the details of a specific cocktail
 */
@AndroidEntryPoint
class CocktailDetailsFragment : BaseFragment<FragmentCocktailDetailsBinding>() {

    private val viewModel: CocktailDetailViewModel by viewModels()
    private val args: CocktailDetailsFragmentArgs by navArgs()
    private lateinit var adapter: IngredientAdapter

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCocktailDetailsBinding {
        return FragmentCocktailDetailsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setImageAndTransitions()
        viewModel.init(args.uiCocktail)
        observeCocktailsDetailUiState()
        observeIngredientsUiState()

    }

    private fun observeCocktailsDetailUiState() {
        viewModel.cocktailDetailsUiState.collectWhileResumed {
            when (it) {
                is CocktailDetailUiState.Loading -> {
                    //binding.progressBar.isVisible = true
                }
                is CocktailDetailUiState.Success -> {
                    binding.collapsingToolbar.title = it.uiCocktail.name
                    binding.instructions.text = it.uiCocktail.instructions

                    adapter = IngredientAdapter(this::onIngredientClicked).also {
                        binding.ingredientsRv.adapter = it
                        binding.ingredientsRv.layoutManager = LinearLayoutManager(requireActivity())
                    }
                    adapter.submitList(args.uiCocktail.ingredients)
                }
                is CocktailDetailUiState.Error -> {
                    //binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun observeIngredientsUiState() {
        viewModel.ingredientsUiState.collectWhileResumed {
            when (it) {
                is IngredientsUiState.Loading -> {}
                is IngredientsUiState.Success -> {
                    showDialog(it.ingredient)
                    viewModel.clearIngredient()
                }
                is IngredientsUiState.Error -> {
                    Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setImageAndTransitions() {
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)
        sharedElementReturnTransition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)
        binding.cocktailIv.apply {
            transitionName = args.uiCocktail.thumb
            Glide.with(binding.root)
                .load(args.uiCocktail.thumb)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }
                })
                .into(this)
        }
        postponeEnterTransition()
    }

    private fun onIngredientClicked(uiIngredientMeasure: UiIngredientMeasure) {
        uiIngredientMeasure.ingredientName?.let { viewModel.getIngredient(it) }
    }

    private fun showDialog(uiIngredient: UiIngredient) {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.ingredient_details_dialog)

        val title = dialog.findViewById(R.id.title) as TextView
        title.text = uiIngredient.name
        val description = dialog.findViewById(R.id.description) as TextView
        uiIngredient.description?.let {
            description.text = it
        }
        val close = dialog.findViewById(R.id.close_popup) as ImageView
        close.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }
}
