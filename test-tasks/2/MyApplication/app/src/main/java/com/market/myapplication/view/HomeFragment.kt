package com.market.myapplication.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.market.myapplication.BuildConfig
import com.market.myapplication.R
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 60
        }
        var link:String =""
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    Log.d("MyLog", "Config params updated: $updated")
                    link = remoteConfig.getString(LINK_KEY)
                    Log.d("MyLog",remoteConfig.getString(LINK_KEY))
                    Toast.makeText(requireContext(), "Fetch and activate succeeded",
                        Toast.LENGTH_SHORT).show()
                    val telMgr: TelephonyManager = activity?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager;
                    val simState = telMgr?.getSimState()?.equals(TelephonyManager.SIM_STATE_READY);
                    if (link.isEmpty()||checkIsEmu()||!simState!!){
                        findNavController().navigate(R.id.action_homeFragment_to_newsFragment)
                    }
                    else {
                        val bundle = bundleOf()
                        bundle.putString(LINK_KEY,link)
                        findNavController().navigate(R.id.action_homeFragment_to_webViewFragment,bundle)
                    }
                } else {
                    Toast.makeText(requireContext(), "Fetch failed",
                        Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_homeFragment_to_newsFragment)
                }
            }
        remoteConfig.setConfigSettingsAsync(configSettings)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    private fun checkIsEmu(): Boolean {
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

    companion object{
        const val JSON_KEY = "googleServices"
        const val LINK_KEY = "webViewUrl"
    }
}