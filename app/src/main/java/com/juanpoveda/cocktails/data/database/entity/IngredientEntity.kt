package com.juanpoveda.cocktails.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ingredient")
data class IngredientEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "type")
    val type: String?,
    @ColumnInfo(name = "isAlcoholic")
    val isAlcoholic: String?,
    @ColumnInfo(name = "ABV")
    val ABV: String?
)
