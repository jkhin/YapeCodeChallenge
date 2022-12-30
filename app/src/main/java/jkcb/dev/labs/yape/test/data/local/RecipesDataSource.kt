package jkcb.dev.labs.yape.test.data.local

import jkcb.dev.labs.yape.test.data.entities.RecipeEntity

interface RecipesDataSource {
    suspend fun getRecipes(): List<RecipeEntity>
    suspend fun saveRecipes(entities: List<RecipeEntity>)
}