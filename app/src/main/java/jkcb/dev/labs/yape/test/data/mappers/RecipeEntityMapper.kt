package jkcb.dev.labs.yape.test.data.mappers

import jkcb.dev.labs.yape.test.data.entities.RecipeEntity
import jkcb.dev.labs.yape.test.data.entities.responses.RecipeResponse
import jkcb.dev.labs.yape.test.utils.Mapper

class RecipeEntityMapper: Mapper<RecipeResponse, RecipeEntity> {
    override fun map(entry: RecipeResponse): RecipeEntity {
        return RecipeEntity(
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