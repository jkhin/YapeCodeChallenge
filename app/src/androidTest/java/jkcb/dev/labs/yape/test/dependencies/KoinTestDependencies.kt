package jkcb.dev.labs.yape.test.dependencies

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.gson.Gson
import jkcb.dev.labs.yape.test.data.cloud.RecipesCloudDataSource
import jkcb.dev.labs.yape.test.data.cloud.RecipesCloudDataSourceImp
import jkcb.dev.labs.yape.test.data.cloud.api.RecipesService
import jkcb.dev.labs.yape.test.data.local.RecipesDataSource
import jkcb.dev.labs.yape.test.data.local.database.RecipesDataBase
import jkcb.dev.labs.yape.test.data.local.database.RecipesDataSourceImp
import jkcb.dev.labs.yape.test.data.mappers.RecipeEntityMapper
import jkcb.dev.labs.yape.test.data.mappers.RecipeMapper
import jkcb.dev.labs.yape.test.data.repositories.RecipesRepositoryImpl
import jkcb.dev.labs.yape.test.di.provideRecipeAPI
import jkcb.dev.labs.yape.test.di.provideRetrofit
import jkcb.dev.labs.yape.test.di.providesGson
import jkcb.dev.labs.yape.test.domain.FilterRecipesUseCase
import jkcb.dev.labs.yape.test.domain.GetRecipesUseCase
import jkcb.dev.labs.yape.test.domain.repositories.RecipesRepository
import jkcb.dev.labs.yape.test.domain.usecases.FilterRecipesUseCaseImp
import jkcb.dev.labs.yape.test.domain.usecases.GetRecipesUseCaseImp
import jkcb.dev.labs.yape.test.ui.mappers.RecipeDomainMapper
import jkcb.dev.labs.yape.test.ui.mappers.RecipeModelMapper
import jkcb.dev.labs.yape.test.ui.viewmodels.RecipesViewModel
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import retrofit2.Retrofit


class KoinTestDependencies : KoinTest {

    private val dbModule = module {
        single {
            Room.databaseBuilder(
                androidApplication(),
                RecipesDataBase::class.java,
                RecipesDataBase.DB_NAME
            ).build()
        }
        single { get<RecipesDataBase>().recipeDao() }
    }

    private val appModule = module {
        viewModel { RecipesViewModel(get(), get(), get(), get()) }
        single { RecipeModelMapper() }
        single { RecipeDomainMapper() }

        single<GetRecipesUseCase> { GetRecipesUseCaseImp(get()) }
        single<FilterRecipesUseCase> { FilterRecipesUseCaseImp() }

        single<RecipesRepository> { RecipesRepositoryImpl(get(), get(), get(), get()) }
        single<RecipesCloudDataSource> { RecipesCloudDataSourceImp(get()) }
        single<RecipesDataSource> { RecipesDataSourceImp(get()) }
        single { RecipeMapper() }
        single { RecipeEntityMapper() }

        single { provideRetrofit(get()) }
        factory { providesGson() }
        factory { provideRecipeAPI(get()) }
    }

    @Before
    fun setupDB_Definition() {
        stopKoin()
        startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
            loadKoinModules(
                listOf(dbModule, appModule)
            )
        }
    }

    @Test
    fun get_RecipesDB_Dependency_ShouldSuccess() {
        assertNotNull(get<RecipesDataBase>())
    }

    @Test
    fun get_GetRecipeEntityMapper_Dependency_ShouldSuccess() {
        assertNotNull(get<RecipeEntityMapper>())
    }

    @Test
    fun get_RecipeDomainMapper_Dependency_ShouldSuccess() {
        assertNotNull(get<RecipeDomainMapper>())
    }

    @Test
    fun get_RecipeMapper_Dependency_ShouldSuccess() {
        assertNotNull(get<RecipeMapper>())
    }

    @Test
    fun get_RecipeEntityMapper_Dependency_ShouldSuccess() {
        assertNotNull(get<RecipeEntityMapper>())
    }

    @Test
    fun get_RecipeModelMapper_Dependency_ShouldSuccess() {
        assertNotNull(get<RecipeModelMapper>())
    }

    @Test
    fun get_Retrofit_Dependency_ShouldSuccess() {
        assertNotNull(get<Retrofit>())
    }

    @Test
    fun get_Gson_Dependency_ShouldSuccess() {
        assertNotNull(get<Gson>())
    }

    @Test
    fun get_RecipeApiService_Dependency_ShouldSuccess() {
        assertNotNull(get<RecipesService>())
    }

    @Test
    fun get_GetRecipesUseCase_Dependency_ShouldSuccess() {
        assertNotNull(get<GetRecipesUseCase>())
    }

    @Test
    fun get_FilterRecipesUseCase_Dependency_ShouldSuccess() {
        assertNotNull(get<FilterRecipesUseCase>())
    }

    @Test
    fun get_RecipesRepository_Dependency_ShouldSuccess() {
        assertNotNull(get<RecipesRepository>())
    }

    @Test
    fun get_RecipesCloudDataSource_Dependency_ShouldSuccess() {
        assertNotNull(get<RecipesCloudDataSource>())
    }

}
