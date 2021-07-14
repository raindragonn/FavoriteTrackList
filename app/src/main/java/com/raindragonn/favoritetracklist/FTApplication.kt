package com.raindragonn.favoritetracklist

import android.app.Application
import com.raindragonn.favoritetracklist.di.appModule
import com.raindragonn.favoritetracklist.di.networkModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class FTApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            printLogger(Level.DEBUG)
            androidContext(this@FTApplication)
            modules(appModule, networkModule)
        }
    }
}