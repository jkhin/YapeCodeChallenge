package jkcb.dev.labs.yape.test.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import jkcb.dev.labs.yape.test.BuildConfig
import jkcb.dev.labs.yape.test.data.cloud.RecipesCloudDataSource
import jkcb.dev.labs.yape.test.data.cloud.RecipesCloudDataSourceImp
import jkcb.dev.labs.yape.test.data.cloud.api.RecipesService
import jkcb.dev.labs.yape.test.data.local.RecipesDataSource
import jkcb.dev.labs.yape.test.data.local.database.RecipesDao
import jkcb.dev.labs.yape.test.data.local.database.RecipesDataBase
import jkcb.dev.labs.yape.test.data.local.database.RecipesDataSourceImp
import jkcb.dev.labs.yape.test.data.mappers.RecipeEntityMapper
import jkcb.dev.labs.yape.test.data.mappers.RecipeMapper
import jkcb.dev.labs.yape.test.data.repositories.RecipesRepositoryImpl
import jkcb.dev.labs.yape.test.domain.FilterRecipesUseCase
import jkcb.dev.labs.yape.test.domain.GetRecipesUseCase
import jkcb.dev.labs.yape.test.domain.repositories.RecipesRepository
import jkcb.dev.labs.yape.test.domain.usecases.FilterRecipesUseCaseImp
import jkcb.dev.labs.yape.test.domain.usecases.GetRecipesUseCaseImp
import jkcb.dev.labs.yape.test.ui.mappers.RecipeDomainMapper
import jkcb.dev.labs.yape.test.ui.mappers.RecipeModelMapper
import jkcb.dev.labs.yape.test.ui.viewmodels.RecipesViewModel
import jkcb.dev.labs.yape.test.utils.RecipesEntityConverter
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun injectModules() = appModules

val appModules by lazy {
    loadKoinModules(
        listOf(
            layersModule,
            databaseModule
        )
    )
}

val uiModule = module {
    // UI section
    viewModel { RecipesViewModel(get(), get(), get(), get()) }
    single { RecipeModelMapper() }
    single { RecipeDomainMapper() }
}

val domainModule = module {
    // Domain section
    single<GetRecipesUseCase> { GetRecipesUseCaseImp(get()) }
    single<FilterRecipesUseCase> { FilterRecipesUseCaseImp() }
}

val dataModule = module {}
val layersModule = module {
    // UI section
    viewModel { RecipesViewModel(get(), get(), get(), get()) }
    single { RecipeModelMapper() }
    single { RecipeDomainMapper() }

    // Domain section
    single<GetRecipesUseCase> { GetRecipesUseCaseImp(get()) }
    single<FilterRecipesUseCase> { FilterRecipesUseCaseImp() }

    // Data section
    single<RecipesRepository> { RecipesRepositoryImpl(get(), get(), get(), get()) }
    single<RecipesCloudDataSource> { RecipesCloudDataSourceImp(get()) }
    single<RecipesDataSource> { RecipesDataSourceImp(get()) }
    single { RecipeMapper() }
    single { RecipeEntityMapper() }

    // Retrofit section
    single { provideRetrofit(get()) }
    factory { providesGson() }
    factory { provideRecipeAPI(get()) }

}

val databaseModule = module {
    single { provideDataBase(androidContext()) }
    single { get<RecipesDataBase>().recipeDao() }
}

fun provideRetrofit(gson: Gson): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

fun providesGson(): Gson = GsonBuilder().create()

fun provideRecipeAPI(retrofit: Retrofit): RecipesService =
    retrofit.create(RecipesService::class.java)

fun provideDataBase(applicationContext: Context): RecipesDataBase {
    return Room.databaseBuilder(
        applicationContext.applicationContext,
        RecipesDataBase::class.java,
        RecipesDataBase.DB_NAME
    )
        .build()
}


