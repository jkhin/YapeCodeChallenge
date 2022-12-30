package jkcb.dev.labs.yape.test.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jkcb.dev.labs.yape.test.domain.FilterRecipesUseCase
import jkcb.dev.labs.yape.test.domain.GetRecipesUseCase
import jkcb.dev.labs.yape.test.domain.entities.FilterParams
import jkcb.dev.labs.yape.test.ui.mappers.RecipeDomainMapper
import jkcb.dev.labs.yape.test.ui.mappers.RecipeModelMapper
import jkcb.dev.labs.yape.test.ui.models.RecipeModel
import jkcb.dev.labs.yape.test.ui.viewmodels.states.RecipesViewState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipesViewModel(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val filterRecipesUseCase: FilterRecipesUseCase,
    private val recipesModelMapper: RecipeModelMapper,
    private val recipesDomainMapper: RecipeDomainMapper
) : ViewModel() {

    private lateinit var _recipes: List<RecipeModel>

    val recipesLiveData: MutableLiveData<RecipesViewState> = MutableLiveData<RecipesViewState>()

    fun getRecipes() = viewModelScope.launch(handler){
        launch(Dispatchers.IO){
            val result = getRecipesUseCase.getRecipes()
            _recipes = result.map(recipesModelMapper::map)
            val state = getSuccessState(_recipes)

            recipesLiveData.postValue(state)
        }
    }

    fun filterRecipes(keyword: String) = viewModelScope.launch(handler){
        launch(Dispatchers.IO) {
            val filterParams = createAndFilterParams(keyword)
            val filterResult = filterRecipesUseCase.filter(filterParams)
            val recipes = filterResult.result.map(recipesModelMapper::map)
            val state = getSuccessState(recipes)

            recipesLiveData.postValue(state)
        }
    }

    private fun createAndFilterParams(keyword: String): FilterParams = FilterParams(
        keyword = keyword,
        recipes = _recipes.map(recipesDomainMapper::map)
    )

    private fun getSuccessState(
        recipes: List<RecipeModel>
    ): RecipesViewState.Success = RecipesViewState.Success(recipes = recipes)

    private fun getLoadingState(
        isLoading: Boolean
    ): RecipesViewState.Loading = RecipesViewState.Loading(isLoading = isLoading)

    private fun getErrorState(
        errorMessage: String
    ): RecipesViewState.Error = RecipesViewState.Error(message = errorMessage)

    private fun emmitLoadingState(isLoading: Boolean) {
        val state = getLoadingState(isLoading)
        recipesLiveData.value = state
    }

    private fun emmitErrorState(message: String) {
        val state = getErrorState(message)
        recipesLiveData.postValue(state)
    }

    private val handler = CoroutineExceptionHandler { _, exception ->
        viewModelScope.launch {
            val errorModelMapper = exception.message.orEmpty()
            emmitErrorState(errorModelMapper)
        }
    }
}