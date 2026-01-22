package com.example.aBmealApp

import androidx.room.*

@Entity(tableName = "recipe_history")
data class RecipeHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "recipe_id")
    val recipeId: Int,

    @ColumnInfo(name = "recipe_name")
    val recipeName: String,

    @ColumnInfo(name = "recipe_image")
    val recipeImage: String? = null,

    @ColumnInfo(name = "ready_in_minutes")
    val readyInMinutes: Int = 0,

    @ColumnInfo(name = "servings")
    val servings: Int = 0,

    @ColumnInfo(name = "ingredients")
    val ingredients: String, // Stored as semicolon-separated string

    @ColumnInfo(name = "instructions")
    val instructions: String = "",

    @ColumnInfo(name = "weather_condition")
    val weatherCondition: String = "",

    @ColumnInfo(name = "temperature")
    val temperature: Double = 0.0,

    @ColumnInfo(name = "used_count")
    val usedCount: Int = 1,

    @ColumnInfo(name = "last_used_date")
    val lastUsedDate: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "first_used_date")
    val firstUsedDate: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false,

    @ColumnInfo(name = "user_rating")
    val userRating: Float = 0f,

    @ColumnInfo(name = "notes")
    val notes: String = ""
)