package jkcb.dev.labs.yape.test.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import jkcb.dev.labs.yape.test.data.entities.RecipeEntity
import jkcb.dev.labs.yape.test.utils.RecipesEntityConverter

@Database(entities = [RecipeEntity::class], version = 1, exportSchema = false)
@TypeConverters(RecipesEntityConverter::class)
abstract class RecipesDataBase : RoomDatabase() {

    abstract fun recipeDao(): RecipesDao

    companion object {
        @Volatile
        private var INSTANCE: RecipesDataBase? = null
        const val DB_NAME = "recipes_database"

        fun getInstance(context: Context): RecipesDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(appContext: Context): RecipesDataBase =
            Room.databaseBuilder(
                appContext.applicationContext,
                RecipesDataBase::class.java,
                DB_NAME
            )
                .addTypeConverter(RecipesEntityConverter::class.java)
                .fallbackToDestructiveMigration()
                .build()


    }
}