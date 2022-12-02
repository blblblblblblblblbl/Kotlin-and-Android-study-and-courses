package com.market.myapplication

import android.app.Application
import com.market.myapplication.dagger_bicycle.DaggerBicycleComponent
import com.market.myapplication.dagger_bicycle.DaggerDaggerBicycleComponent
import com.market.myapplication.dagger_bicycle.FrameFactory
import com.market.myapplication.draft.DaggerInfoComponent
import com.market.myapplication.koin_bicycle.bicycleModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp:Application() {
    lateinit var dagger: DaggerBicycleComponent
    val frameFactory = FrameFactory()
    override fun onCreate() {
        super.onCreate()
        dagger = DaggerDaggerBicycleComponent.builder().build()//DaggerDaggerInfoComponent.builder().build()
        startKoin {
            androidContext(applicationContext)
            modules(bicycleModule)
        }
    }
}