package com.market.myapplication.viewModel

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.os.Build
import android.telephony.TelephonyManager
import android.util.Log
import androidx.lifecycle.ViewModel
import com.market.myapplication.BuildConfig
import com.market.myapplication.data.persistent_storage.PersistentStorage
import com.market.myapplication.domain.LocalUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val localUrlUseCase: LocalUrlUseCase,
    @ApplicationContext private val context: Context
):ViewModel() {
    fun getUrl():String{
        return  localUrlUseCase.getUrl()?:""
    }
    fun saveUrl(url:String){
        localUrlUseCase.saveUrl(url)
    }
    fun checkIsEmu(): Boolean {
        if (BuildConfig.DEBUG) return false // when developer use this build on
        val phoneModel = Build.MODEL
        val buildProduct = Build.PRODUCT
        val buildHardware = Build.HARDWARE
        val brand:String = Build.BRAND;
        var result = (Build.FINGERPRINT.startsWith("generic")
                || phoneModel.contains("google_sdk")
                || phoneModel.lowercase(Locale.getDefault()).contains("droid4x")
                || phoneModel.contains("Emulator")
                || phoneModel.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || buildHardware == "goldfish"
                || Build.BRAND.contains("google")
                || Build.SERIAL.contains("unknown")
                || buildHardware == "vbox86"
                || buildProduct == "sdk"
                || buildProduct == "google_sdk"
                || buildProduct == "sdk_x86"
                || buildProduct == "vbox86p"
                || Build.BOARD.lowercase(Locale.getDefault()).contains("nox")
                || Build.BOOTLOADER.lowercase(Locale.getDefault()).contains("nox")
                || buildHardware.lowercase(Locale.getDefault()).contains("nox")
                || buildProduct.lowercase(Locale.getDefault()).contains("nox"))
        if (result) return true
        result = result or (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
        if (result) return true
        result = result or ("google_sdk" == buildProduct)
        return result
    }
    fun checkSim():Boolean{
        val telMgr: TelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager;
        val simState = telMgr?.getSimState()?.equals(TelephonyManager.SIM_STATE_READY);
        return simState?:false
    }
    fun isDeviceOnline(): Boolean {
        val connManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val tagLog = "MyLog"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities =  connManager.getNetworkCapabilities(connManager.activeNetwork)
            if (networkCapabilities == null) {
                Log.d(tagLog, "Device Offline")
                return false
            }
            else {
                Log.d(tagLog, "Device Online")
                return true
            }
        } else {
            // below Marshmallow
            val activeNetwork = connManager.activeNetworkInfo
            if (activeNetwork?.isConnectedOrConnecting == true && activeNetwork.isAvailable) {
                Log.d(tagLog, "Device Online")
                return true
            }
            else {
                Log.d(tagLog, "Device Offline")
                return false
            }
        }
    }
}