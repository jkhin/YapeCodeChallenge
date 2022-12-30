package jkcb.dev.labs.yape.test.ui.viewmodels.states

import jkcb.dev.labs.yape.test.ui.models.RecipeModel

open class RecipesViewState {
    class Loading(val isLoading: Boolean): RecipesViewState()
    class Success(val recipes: List<RecipeModel>): RecipesViewState()
    class Error(val message: String): RecipesViewState()
}