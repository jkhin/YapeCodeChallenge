package jkcb.dev.labs.yape.test.data.cloud

import jkcb.dev.labs.yape.test.data.entities.responses.RecipeResponse


interface RecipesCloudDataSource {
    suspend fun getRecipes(): List<RecipeResponse>
}