package jkcb.dev.labs.yape.test.domain

import jkcb.dev.labs.yape.test.domain.entities.FilterParams
import jkcb.dev.labs.yape.test.domain.entities.FilterResult

interface FilterRecipesUseCase {
    suspend fun filter(params: FilterParams): FilterResult
}