package com.raindragonn.favoritetracklist

import android.app.Application
import com.raindragonn.favoritetracklist.di.appModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class FTApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            if(BuildConfig.DEBUG){
                printLogger(Level.DEBUG)
            }
            androidContext(this@FTApplication)
            modules(appModule)
        }
    }
}