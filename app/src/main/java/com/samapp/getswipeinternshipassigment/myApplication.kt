package com.samapp.getswipeinternshipassigment

import android.app.Application
import com.samapp.getswipeinternshipassigment.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            printLogger(Level.DEBUG)
            androidContext(this@MyApplication)
            modules(AppModule)
        }
    }
}