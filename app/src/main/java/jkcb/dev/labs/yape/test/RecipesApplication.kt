package jkcb.dev.labs.yape.test

import android.app.Application
import jkcb.dev.labs.yape.test.di.injectModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RecipesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }

    private fun initializeKoin() {
        startKoin {
            androidLogger()
            androidContext(this@RecipesApplication)
            injectModules()
        }
    }

}