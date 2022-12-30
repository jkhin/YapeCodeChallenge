package jkcb.dev.labs.yape.test.data.repositories

import jkcb.dev.labs.yape.test.data.cloud.RecipesCloudDataSource
import jkcb.dev.labs.yape.test.data.local.RecipesDataSource
import jkcb.dev.labs.yape.test.data.mappers.RecipeEntityMapper
import jkcb.dev.labs.yape.test.data.mappers.RecipeMapper
import jkcb.dev.labs.yape.test.domain.entities.Recipe
import jkcb.dev.labs.yape.test.domain.repositories.RecipesRepository

class RecipesRepositoryImpl(
    private val recipesDataSource: RecipesDataSource,
    private val recipesCloudDataSource: RecipesCloudDataSource,
    private val recipeEntityMapper: RecipeEntityMapper,
    private val recipeMapper: RecipeMapper,
) : RecipesRepository {

    override suspend fun getRecipes(): List<Recipe> {
        val recipes = recipesDataSource.getRecipes()
        return if (recipes.isNotEmpty()) {
            recipes.map(recipeMapper::map)
        } else {
            val response = recipesCloudDataSource.getRecipes()
            val entities = response.map(recipeEntityMapper::map)
            recipesDataSource.saveRecipes(entities)
            return entities.map(recipeMapper::map)
        }
    }
}
