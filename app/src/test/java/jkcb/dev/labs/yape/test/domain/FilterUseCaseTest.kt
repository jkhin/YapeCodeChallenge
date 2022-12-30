package jkcb.dev.labs.yape.test.domain

import jkcb.dev.labs.yape.test.domain.entities.FilterParams
import jkcb.dev.labs.yape.test.domain.entities.Recipe
import jkcb.dev.labs.yape.test.domain.usecases.FilterRecipesUseCaseImp
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FilterUseCaseTest {

    private val filterRecipesUseCase: FilterRecipesUseCase = FilterRecipesUseCaseImp()

    private val KEYWORD_0 = ""
    private val KEYWORD_1 = "palta"
    private val KEYWORD_2 = "olla"

    private val recipesMockk = listOf(
        Recipe(
            img = "https://s1.eestatic.com/2015/02/09/cocinillas/cocinillas_9759205_116075769_1024x688.jpg",
            title = "Ingredient11285 al Sol",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            preparation = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo...",
            ingredients = listOf(
                "Ingredient1",
                "Ingredient123",
                "Ingredient11285",
                "Ingredient9883"
            ),
            tags = listOf("Tag1", "Tag2", "Tag8", "Tag9", "olla"),
            lat = -12.04318,
            lng = -77.02824
        ),
        Recipe(
            img = "https://s1.eestatic.com/2015/02/20/cocinillas/cocinillas_12508899_116091127_1024x683.jpg",
            title = "Ingredient9883 al sarten",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            preparation = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo...",
            ingredients = listOf("Ingredient0", "Ingredient2374", "Ingredient9883", "sarten"),
            tags = listOf("Tag1", "Tag2", "Tag8", "Tag9"),
            lat = -14.06777,
            lng = -75.72861
        ),
        Recipe(
            img = "https://s1.eestatic.com/2015/02/09/cocinillas/cocinillas_9759205_116075769_1024x688.jpg",
            title = "Choclo al Sol",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            preparation = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo...",
            ingredients = listOf(
                "Ingredient1",
                "Ingredient123",
                "Ingredient11285",
                "choclo",
                "maiz",
                "popcorn",
                "tasty"
            ),
            tags = listOf("Tag1", "Tag2", "Tag8", "palta", "olla"),
            lat = -12.04318,
            lng = -77.02824
        ),
        Recipe(
            img = "https://s1.eestatic.com/2015/01/26/cocinillas/cocinillas_6259524_116057239_1024x672.jpg",
            title = "Ingredient0 salty spicy",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?",
            preparation = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo...",
            ingredients = listOf("Ingredient0", "miel", "salt", "sarten"),
            tags = listOf(
                "Tag1982",
                "Happy",
                "Lorem",
                "pollo",
                "chicken",
                "meat",
                "onion",
                "tomate",
                "tomato",
                "lettuce",
                "photo",
                "yumy",
                "tasty"
            ),
            lat = -9.52779,
            lng = -77.52778
        )
    )

    @Test
    fun filteringList_ByValidKeyword_ReturnTrue() = runBlocking {
        val params = FilterParams(keyword = KEYWORD_1, recipes = recipesMockk)
        val filterResult = filterRecipesUseCase.filter(params)
        assertFalse(filterResult.result.isEmpty())
        assertEquals(1, filterResult.result.size)
    }

    @Test
    fun filteringList_ByEmptyKeyword_ReturnTrue() = runBlocking {
        val params = FilterParams(keyword = KEYWORD_0, recipes = recipesMockk)
        val filterResult = filterRecipesUseCase.filter(params)
        assertTrue(filterResult.result.isNotEmpty())
        assertEquals(4, filterResult.result.size)
        assertTrue(recipesMockk.containsAll(filterResult.result))
    }

    @Test
    fun filteringList_ByValidKeyword2_ReturnTrue() = runBlocking {
        val params = FilterParams(keyword = KEYWORD_2, recipes = recipesMockk)
        val filterResult = filterRecipesUseCase.filter(params)
        assertTrue(filterResult.result.isNotEmpty())
        assertEquals(2, filterResult.result.size)
        assertTrue(recipesMockk.containsAll(filterResult.result))
    }

}