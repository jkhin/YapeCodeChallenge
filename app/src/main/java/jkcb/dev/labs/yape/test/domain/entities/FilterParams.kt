package jkcb.dev.labs.yape.test.domain.entities

data class FilterParams(
    val keyword: String,
    val recipes: List<Recipe>
)