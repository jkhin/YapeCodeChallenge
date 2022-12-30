package jkcb.dev.labs.yape.test.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val img: String,
    val description: String,
    val preparation: String,
    val ingredients: List<String>,
    val tags: List<String>,
    val lat: Double,
    val lng: Double
) {
    constructor(
        title: String,
        img: String,
        description: String,
        preparation: String,
        ingredients: List<String>,
        tags: List<String>,
        lat: Double,
        lng: Double
    ) : this(
        0,
        title,
        img,
        description,
        preparation,
        ingredients = ingredients,
        tags = tags,
        lat,
        lng
    )

}