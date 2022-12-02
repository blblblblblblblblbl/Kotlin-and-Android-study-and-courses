package com.market.myapplication.koin_bicycle

import com.market.myapplication.dagger_bicycle.BicycleFactory
import com.market.myapplication.dagger_bicycle.FrameFactory
import com.market.myapplication.dagger_bicycle.WheelDealer
import org.koin.dsl.module
import javax.inject.Named

    val bicycleModule = module {
        single<WheelDealer> { provideWheelDealer() }
        single<FrameFactory> { provideFrameFactory() }
        factory<BicycleFactory> { provideBicycleFactory(get(),get()) }
    }
    private fun provideWheelDealer(): WheelDealer = WheelDealer()
    private fun provideFrameFactory(): FrameFactory = FrameFactory()
    private fun provideBicycleFactory(
        wheelDealer: WheelDealer,
        frameFactory: FrameFactory
    ): BicycleFactory {
        return BicycleFactory(wheelDealer,frameFactory)
    }
