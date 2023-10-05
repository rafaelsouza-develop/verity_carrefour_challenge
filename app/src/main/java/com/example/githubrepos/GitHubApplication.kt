package com.example.githubrepos

import android.app.Application
import android.content.Context
import com.example.githubrepos.di.repositoryModule
import com.example.githubrepos.di.serviceModule
import com.example.githubrepos.di.viewModelModule
import com.example.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GitHubApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupApp(baseContext)
    }

    private fun setupApp(context: Context) {
        startKoin {
            androidContext(context)
            modules(
                listOf(
                    networkModule,
                    serviceModule,
                    viewModelModule,
                    repositoryModule
                )
            )
        }
    }
}