package com.juanpoveda.cocktails.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.juanpoveda.cocktails.databinding.CocktailListItemBinding
import com.juanpoveda.cocktails.presentation.model.UiCocktail

typealias OnCocktailClickListener = (UiCocktail, ImageView) -> Unit

class CocktailAdapter(
    private val onCocktailClickListener: OnCocktailClickListener
) : ListAdapter<UiCocktail, CocktailAdapter.CocktailViewHolder>(CocktailListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val binding = CocktailListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CocktailViewHolder(binding, onCocktailClickListener)
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CocktailViewHolder(
        private val binding: CocktailListItemBinding,
        private val onCocktailClickListener: OnCocktailClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cocktail: UiCocktail) {
            binding.thumbnail.apply {
                transitionName = cocktail.thumb
                Glide.with(binding.root).load(cocktail.thumb).into(this)
            }
            binding.title.apply {
                transitionName = cocktail.name
                text = cocktail.name
            }
            binding.root.setOnClickListener { onCocktailClickListener(cocktail, binding.thumbnail) }
        }
    }

}

class CocktailListDiffCallback : DiffUtil.ItemCallback<UiCocktail>() {

    override fun areItemsTheSame(oldItem: UiCocktail, newItem: UiCocktail): Boolean {
        return oldItem.id.equals(newItem.id, true)
    }

    override fun areContentsTheSame(oldItem: UiCocktail, newItem: UiCocktail): Boolean {
        return oldItem == newItem
    }

}
