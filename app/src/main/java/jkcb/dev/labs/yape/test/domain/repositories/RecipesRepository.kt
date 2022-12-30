package jkcb.dev.labs.yape.test.domain.repositories

import jkcb.dev.labs.yape.test.domain.entities.Recipe

interface RecipesRepository {
    suspend fun getRecipes(): List<Recipe>
}