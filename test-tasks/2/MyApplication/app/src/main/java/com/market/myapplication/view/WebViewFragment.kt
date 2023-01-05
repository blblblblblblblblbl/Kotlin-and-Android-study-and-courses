package com.market.myapplication.view

import android.annotation.TargetApi
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import com.market.myapplication.databinding.FragmentWebViewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WebViewFragment : Fragment() {
    lateinit var binding: FragmentWebViewBinding
    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebViewBinding.inflate(layoutInflater)
        val webView =binding.webview
        webView.settings.javaScriptEnabled=true
        webView.settings.domStorageEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://www.google.com")
        return binding.root
    }*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                WebViewPage("https://www.google.com")
            }
        }
    }
    @Composable
    fun WebViewPage(url: String){

        var backEnabled by remember { mutableStateOf(false) }
        var webView: WebView? = null

        AndroidView(
            factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = WebViewClient()

                    // to play video on a web view
                    settings.javaScriptEnabled = true

                    webViewClient = object : WebViewClient() {

                        override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
                            backEnabled = view.canGoBack()
                        }

                    }

                    loadUrl(url)
                    webView = this
                }
            }, update = {
                webView = it
                //  it.loadUrl(url)
            })


        BackHandler(enabled = backEnabled) {
            webView?.goBack()
        }

    }

}