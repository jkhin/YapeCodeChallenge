package jkcb.dev.labs.yape.test.data.cloud.api

import jkcb.dev.labs.yape.test.data.entities.responses.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET

interface RecipesService {

    @GET("recipes")
    suspend fun getRecipes(): Response<List<RecipeResponse>>

}
