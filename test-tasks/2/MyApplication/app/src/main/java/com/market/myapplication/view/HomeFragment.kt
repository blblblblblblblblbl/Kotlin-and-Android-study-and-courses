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
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.compose.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.market.myapplication.BuildConfig
import com.market.myapplication.R
import com.market.myapplication.viewModel.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel:HomeFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 60
        }
        var link:String = viewModel.getUrl()
        if (link.isNotEmpty()){
            if (viewModel.isDeviceOnline()) {
                val bundle = bundleOf()
                bundle.putString(LINK_KEY,link)
                findNavController().navigate(R.id.action_homeFragment_to_webViewFragment,bundle)
            }
            else{
                return ComposeView(requireContext()).apply {
                    setContent {
                        NoConnectionAnim()
                    }
                }
            }
        }
        else{
            remoteConfig.fetchAndActivate()
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        val updated = task.result
                        Log.d("MyLog", "Config params updated: $updated")
                        link = remoteConfig.getString(LINK_KEY)
                        Log.d("MyLog",remoteConfig.getString(LINK_KEY))
                        Toast.makeText(requireContext(), "Fetch and activate succeeded",
                            Toast.LENGTH_SHORT).show()
                        if (link.isEmpty()||viewModel.checkIsEmu()||!viewModel.checkSim()){
                            findNavController().navigate(R.id.action_homeFragment_to_newsFragment)
                        }
                        else {
                            viewModel.saveUrl(link)
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
        }

        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    @Composable
    fun MyLottieAnimation() {
        var isLottiePlaying by remember {
            mutableStateOf(true)
        }
        var animationSpeed by remember {
            mutableStateOf(1f)
        }
        val composition by rememberLottieComposition(
            LottieCompositionSpec
                .RawRes(R.raw.no_internet_connection)
        )

        // to control the lottie animation
        val lottieAnimation by animateLottieCompositionAsState(
            // pass the composition created above
            composition,
            // Iterates Forever
            iterations = LottieConstants.IterateForever,
            // Lottie and pause/play
            isPlaying = isLottiePlaying
        )
        LottieAnimation(
            composition,
            lottieAnimation
        )
    }
    @Composable
    fun NoConnectionAnim() {

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.White),
            verticalArrangement = Arrangement.Center
        ) {
            MyLottieAnimation()
        }
    }
    companion object{
        const val JSON_KEY = "googleServices"
        const val LINK_KEY = "webViewUrl"
    }
}