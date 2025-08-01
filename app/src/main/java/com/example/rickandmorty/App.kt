package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.di.networkModule
import com.example.rickandmorty.di.repositoryModule
import com.example.rickandmorty.di.roomModule
import com.example.rickandmorty.di.useCaseModule
import com.example.rickandmorty.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                roomModule,
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}
