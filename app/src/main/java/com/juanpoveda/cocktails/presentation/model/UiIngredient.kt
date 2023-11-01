package com.juanpoveda.cocktails.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UiIngredient (
    val id: String,
    val name: String?,
    val description: String?,
    val type: String?,
    val isAlcoholic: String?,
    val ABV: String?
) : Parcelable
