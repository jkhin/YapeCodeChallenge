package jkcb.dev.labs.yape.test.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jkcb.dev.labs.yape.test.data.entities.RecipeEntity

@Dao
interface RecipesDao {

    @Query("SELECT * FROM recipes")
    fun getRecipes(): List<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: List<RecipeEntity>)

}
