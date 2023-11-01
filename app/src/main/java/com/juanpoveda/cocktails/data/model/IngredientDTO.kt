package com.juanpoveda.cocktails.data.model

import com.google.gson.annotations.SerializedName

data class IngredientsDTO(
    val ingredients: List<IngredientDTO>
)

data class IngredientDTO(
    @SerializedName("idIngredient")
    val id: String,
    @SerializedName("strIngredient")
    val name: String?,
    @SerializedName("strDescription")
    val description: String?,
    @SerializedName("strType")
    val type: String?,
    @SerializedName("strAlcohol")
    val isAlcoholic: String?,
    @SerializedName("strABV")
    val ABV: String?
)
