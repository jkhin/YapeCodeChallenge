package jkcb.dev.labs.yape.test.data.cloud

import jkcb.dev.labs.yape.test.data.cloud.api.RecipesService
import jkcb.dev.labs.yape.test.data.cloud.base.CloudDataSource
import jkcb.dev.labs.yape.test.data.entities.responses.RecipeResponse

class RecipesCloudDataSourceImp(
    private val recipesService: RecipesService
) : CloudDataSource(), RecipesCloudDataSource {

    override suspend fun getRecipes(): List<RecipeResponse> = getResult { recipesService.getRecipes() }

}
