package jkcb.dev.labs.yape.test.ui.mappers

import jkcb.dev.labs.yape.test.domain.entities.Recipe
import jkcb.dev.labs.yape.test.ui.models.RecipeModel
import jkcb.dev.labs.yape.test.utils.Mapper

class RecipeModelMapper : Mapper<Recipe, RecipeModel> {
    override fun map(entry: Recipe): RecipeModel {
        return RecipeModel(
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