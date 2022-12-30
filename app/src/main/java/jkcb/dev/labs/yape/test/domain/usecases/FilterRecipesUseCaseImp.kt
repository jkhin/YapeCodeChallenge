package jkcb.dev.labs.yape.test.domain.usecases

import jkcb.dev.labs.yape.test.domain.FilterRecipesUseCase
import jkcb.dev.labs.yape.test.domain.entities.FilterParams
import jkcb.dev.labs.yape.test.domain.entities.FilterResult

class FilterRecipesUseCaseImp: FilterRecipesUseCase {

    override suspend fun filter(params: FilterParams): FilterResult {
        val result = if (params.keyword.isNotBlank()) {
            params.recipes.filter {
                it.title.contains(params.keyword, true) or
                        it.ingredients.filterByIngredient(params.keyword) or
                        it.tags.filterByTags(params.keyword)
            }
        } else params.recipes

        return FilterResult(result = result)
    }

    private fun List<String>.filterByIngredient(
        keyword: String
    ) = any { ingredient -> ingredient.contains(keyword, true) }

    private fun List<String>.filterByTags(
        keyword: String
    ) = any { tags -> tags.contains(keyword,true) }
}