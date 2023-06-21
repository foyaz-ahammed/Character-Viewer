package com.sample.simpsonsviewer

import android.app.Application
import com.sample.simpsonsviewer.modules.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CharacterViewApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CharacterViewApp)
            modules(appModule)
        }
    }
}