package com.juanpoveda.cocktails.domain.model

class Cocktail (
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
    val ingredients: HashMap<String?, String?>,
    val imageSource: String?,
    val dateModified: String?
)
