package com.market.myapplication.draft

import com.market.myapplication.MainActivity
import com.market.myapplication.dagger_bicycle.Bicycle
import com.market.myapplication.dagger_bicycle.BicycleFactory
import com.market.myapplication.dagger_bicycle.BicycleModule
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

class Info(val text:String)

@Module
class DaggerInfoModule{
    @Provides
    fun provideInfo():Info = Info("AAAAAAAAAA")
}
@Singleton
@Component(modules = [DaggerInfoModule::class,BicycleModule::class])
interface DaggerInfoComponent{
    //fun inject(app:MainActivity)
    //fun getBicycleFactory():BicycleFactory
}
