package jkcb.dev.labs.yape.test.domain.usecases

import jkcb.dev.labs.yape.test.domain.GetRecipesUseCase
import jkcb.dev.labs.yape.test.domain.repositories.RecipesRepository

class GetRecipesUseCaseImp(
    private val recipesRepository: RecipesRepository
): GetRecipesUseCase {

    override suspend fun getRecipes() = recipesRepository.getRecipes()

}