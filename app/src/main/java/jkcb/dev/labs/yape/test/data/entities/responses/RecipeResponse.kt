package jkcb.dev.labs.yape.test.data.entities.responses

data class RecipeResponse(
    val title: String,
    val img: String,
    val description: String,
    val preparation: String,
    val ingredients: List<String>,
    val tags: List<String>,
    val lat: Double,
    val lng: Double,
)