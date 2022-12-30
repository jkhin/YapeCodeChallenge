package jkcb.dev.labs.yape.test.domain

import jkcb.dev.labs.yape.test.domain.entities.Recipe

interface GetRecipesUseCase {
    suspend fun getRecipes(): List<Recipe>
}