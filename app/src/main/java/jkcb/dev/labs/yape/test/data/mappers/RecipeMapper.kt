package jkcb.dev.labs.yape.test.data.mappers

import jkcb.dev.labs.yape.test.data.entities.RecipeEntity
import jkcb.dev.labs.yape.test.domain.entities.Recipe
import jkcb.dev.labs.yape.test.utils.Mapper

class RecipeMapper: Mapper<RecipeEntity, Recipe> {
    override fun map(entry: RecipeEntity): Recipe {
        return Recipe(
            title = entry.title,
            img = entry.img,
            description = entry.description,
            preparation = entry.preparation,
            ingredients = entry.ingredients,
            tags = entry.tags,
            lat = entry.lat,
            lng = entry.lng
        )
    }
}