package jkcb.dev.labs.yape.test.domain.entities

data class Recipe(
    val title: String,
    val img: String,
    val description: String,
    val preparation: String,
    val ingredients: List<String>,
    val tags: List<String>,
    val lat: Double,
    val lng: Double
)