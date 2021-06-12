package com.luthtan.cinemajetpack

import android.app.Application
import com.luthtan.cinemajetpack.di.module.appModule
import com.luthtan.cinemajetpack.di.module.repoModule
import com.luthtan.cinemajetpack.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(appModule, viewModelModule, repoModule))
        }
    }
}