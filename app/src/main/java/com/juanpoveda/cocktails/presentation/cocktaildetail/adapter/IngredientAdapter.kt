package com.juanpoveda.cocktails.presentation.cocktaildetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.juanpoveda.cocktails.databinding.IngredientListItemBinding
import com.juanpoveda.cocktails.presentation.model.UiIngredientMeasure

typealias OnIngredientClickListener = (UiIngredientMeasure) -> Unit

class IngredientAdapter(
    private val onIngredientClickListener: OnIngredientClickListener
) : ListAdapter<UiIngredientMeasure, IngredientAdapter.IngredientViewHolder>(IngredientListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val binding = IngredientListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngredientViewHolder(binding, onIngredientClickListener)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class IngredientViewHolder(
        private val binding: IngredientListItemBinding,
        private val onIngredientClickListener: OnIngredientClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ingredient: UiIngredientMeasure) {
            binding.ingredientName.text = ingredient.ingredientName
            binding.ingredientMeasure.text = ingredient.measure
            binding.root.setOnClickListener { onIngredientClickListener(ingredient) }
        }
    }

}

class IngredientListDiffCallback : DiffUtil.ItemCallback<UiIngredientMeasure>() {

    override fun areItemsTheSame(oldItem: UiIngredientMeasure, newItem: UiIngredientMeasure): Boolean {
        return oldItem.ingredientName.equals(newItem.ingredientName, true)
    }

    override fun areContentsTheSame(oldItem: UiIngredientMeasure, newItem: UiIngredientMeasure): Boolean {
        return oldItem == newItem
    }

}
