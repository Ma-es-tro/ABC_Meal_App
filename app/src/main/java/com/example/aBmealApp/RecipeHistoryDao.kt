package com.example.aBmealApp

import androidx.room.*

@Dao
interface RecipeHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeHistoryEntity): Long

    @Update
    suspend fun updateRecipe(recipe: RecipeHistoryEntity)

    @Delete
    suspend fun deleteRecipe(recipe: RecipeHistoryEntity)

    @Query("SELECT * FROM recipe_history ORDER BY last_used_date DESC")
    suspend fun getAllRecipes(): List<RecipeHistoryEntity>

    @Query("SELECT * FROM recipe_history WHERE recipe_id = :recipeId LIMIT 1")
    suspend fun getRecipeById(recipeId: Int): RecipeHistoryEntity?

    @Query("SELECT * FROM recipe_history WHERE is_favorite = 1 ORDER BY last_used_date DESC")
    suspend fun getFavoriteRecipes(): List<RecipeHistoryEntity>

    @Query("SELECT * FROM recipe_history ORDER BY used_count DESC LIMIT :limit")
    suspend fun getMostUsedRecipes(limit: Int = 10): List<RecipeHistoryEntity>

    @Query("SELECT * FROM recipe_history ORDER BY last_used_date DESC LIMIT :limit")
    suspend fun getRecentRecipes(limit: Int = 10): List<RecipeHistoryEntity>

    @Query("SELECT * FROM recipe_history WHERE weather_condition = :weatherCondition ORDER BY used_count DESC")
    suspend fun getRecipesByWeather(weatherCondition: String): List<RecipeHistoryEntity>

    @Query("SELECT * FROM recipe_history WHERE recipe_name LIKE '%' || :query || '%' ORDER BY last_used_date DESC")
    suspend fun searchRecipes(query: String): List<RecipeHistoryEntity>

    @Query("DELETE FROM recipe_history WHERE recipe_id = :recipeId")
    suspend fun deleteRecipeById(recipeId: Int)

    @Query("DELETE FROM recipe_history")
    suspend fun clearAllHistory()

    @Query("UPDATE recipe_history SET is_favorite = :isFavorite WHERE recipe_id = :recipeId")
    suspend fun updateFavoriteStatus(recipeId: Int, isFavorite: Boolean)

    @Query("UPDATE recipe_history SET user_rating = :rating WHERE recipe_id = :recipeId")
    suspend fun updateRating(recipeId: Int, rating: Float)

    @Query("UPDATE recipe_history SET notes = :notes WHERE recipe_id = :recipeId")
    suspend fun updateNotes(recipeId: Int, notes: String)
}