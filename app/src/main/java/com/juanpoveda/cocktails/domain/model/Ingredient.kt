package com.juanpoveda.cocktails.domain.model

import com.google.gson.annotations.SerializedName

class Ingredient (
    val id: String,
    val name: String?,
    val description: String?,
    val type: String?,
    val isAlcoholic: String?,
    val ABV: String?
)
