package com.juanpoveda.cocktails.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UiCocktail (
    val id: String,
    val name: String?,
    val alternate: String?,
    val tags: String?,
    val video: String?,
    val category: String?,
    val IBA: String?,
    val type: String?,
    val glass: String?,
    val instructions: String?,
    val instructionsES: String?,
    val thumb: String?,
    val ingredients: List<UiIngredientMeasure>,
    val imageSource: String?,
    val dateModified: String?
): Parcelable
