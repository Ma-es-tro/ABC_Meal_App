package com.example.aBmealApp

import android.util.Log

class RecipeRepository(private val recipeDao: RecipeHistoryDao) {

    suspend fun saveRecipe(recipe: RecipeDetails, weatherCondition: WeatherCondition) {
        val existingRecipe = recipeDao.getRecipeById(recipe.id)

        if (existingRecipe != null) {
            // Recipe already exists - increment usage count
            val updated = existingRecipe.copy(
                usedCount = existingRecipe.usedCount + 1,
                lastUsedDate = System.currentTimeMillis(),
                weatherCondition = weatherCondition.type,
                temperature = weatherCondition.temperature
            )
            recipeDao.updateRecipe(updated)
            Log.d("RecipeDB", "Updated recipe: ${recipe.title}, count: ${updated.usedCount}")
        } else {
            // New recipe - insert it
            val ingredientsJson = recipe.extendedIngredients.joinToString(";") { it.original }
            val newRecipe = RecipeHistoryEntity(
                recipeId = recipe.id,
                recipeName = recipe.title,
                recipeImage = recipe.image,
                readyInMinutes = recipe.readyInMinutes,
                servings = recipe.servings,
                ingredients = ingredientsJson,
                instructions = recipe.instructions,
                weatherCondition = weatherCondition.type,
                temperature = weatherCondition.temperature
            )
            recipeDao.insertRecipe(newRecipe)
            Log.d("RecipeDB", "Inserted new recipe: ${recipe.title}")
        }
    }

    suspend fun getAllRecipes(): List<RecipeHistoryEntity> {
        return recipeDao.getAllRecipes()
    }

    suspend fun getFavorites(): List<RecipeHistoryEntity> {
        return recipeDao.getFavoriteRecipes()
    }

    suspend fun getMostUsed(limit: Int = 10): List<RecipeHistoryEntity> {
        return recipeDao.getMostUsedRecipes(limit)
    }

    suspend fun getRecent(limit: Int = 10): List<RecipeHistoryEntity> {
        return recipeDao.getRecentRecipes(limit)
    }

    suspend fun getByWeather(weatherCondition: String): List<RecipeHistoryEntity> {
        return recipeDao.getRecipesByWeather(weatherCondition)
    }

    suspend fun searchRecipes(query: String): List<RecipeHistoryEntity> {
        return recipeDao.searchRecipes(query)
    }

    suspend fun toggleFavorite(recipeId: Int, isFavorite: Boolean) {
        recipeDao.updateFavoriteStatus(recipeId, isFavorite)
    }

    suspend fun updateRating(recipeId: Int, rating: Float) {
        recipeDao.updateRating(recipeId, rating)
    }

    suspend fun updateNotes(recipeId: Int, notes: String) {
        recipeDao.updateNotes(recipeId, notes)
    }

    suspend fun deleteRecipe(recipeId: Int) {
        recipeDao.deleteRecipeById(recipeId)
    }

    suspend fun clearAll() {
        recipeDao.clearAllHistory()
    }
}