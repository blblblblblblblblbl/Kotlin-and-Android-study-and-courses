package com.blblblbl.mvvm1timer

import android.app.Application
//import leakcanary.LeakCanary

class MyApplication:Application() {
    lateinit var viewModel: ViewModel
    override fun onCreate() {
        super.onCreate()
        viewModel = ViewModel(Model())
    }
}