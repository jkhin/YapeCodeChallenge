package jkcb.dev.labs.yape.test.data.local.database

import jkcb.dev.labs.yape.test.data.entities.RecipeEntity
import jkcb.dev.labs.yape.test.data.local.RecipesDataSource

class RecipesDataSourceImp(
    private val recipesDao: RecipesDao
): RecipesDataSource {
    override suspend fun getRecipes(): List<RecipeEntity> = recipesDao.getRecipes()
    override suspend fun saveRecipes(entities: List<RecipeEntity>) = recipesDao.insert(entities)
}