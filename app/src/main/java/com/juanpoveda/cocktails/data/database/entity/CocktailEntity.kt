package com.juanpoveda.cocktails.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cocktail")
data class CocktailEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "alternate")
    val alternate: String?,
    @ColumnInfo(name = "tags")
    val tags: String?,
    @ColumnInfo(name = "video")
    val video: String?,
    @ColumnInfo(name = "category")
    val category: String?,
    @ColumnInfo(name = "IBA")
    val IBA: String?,
    @ColumnInfo(name = "type")
    val type: String?,
    @ColumnInfo(name = "glass")
    val glass: String?,
    @ColumnInfo(name = "instructions")
    val instructions: String?,
    @ColumnInfo(name = "instructionsES")
    val instructionsES: String?,
    @ColumnInfo(name = "drink_thumb")
    val drinkThumb: String?,
    @ColumnInfo(name = "ingredients")
    val ingredients: HashMap<String?, String?>,
    @ColumnInfo(name = "image_source")
    val imageSource: String?,
    @ColumnInfo(name = "date_modified")
    val dateModified: String?,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)
