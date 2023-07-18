package com.vitoksmile.kmm.health

import android.app.Application
import com.vitoksmile.kmm.health.koin.attachHealthKMM
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class HealthApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@HealthApplication)
            androidLogger()
            attachHealthKMM(this@HealthApplication)
        }
    }
}