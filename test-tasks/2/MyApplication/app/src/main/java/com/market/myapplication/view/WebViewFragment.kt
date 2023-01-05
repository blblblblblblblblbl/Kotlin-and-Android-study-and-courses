package com.market.myapplication.view

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.market.myapplication.databinding.FragmentWebViewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WebViewFragment : Fragment() {
    lateinit var binding: FragmentWebViewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebViewBinding.inflate(layoutInflater)
        val webView =binding.webview
        webView.settings.javaScriptEnabled=true
        webView.settings.domStorageEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://www.google.com")
        /*var link:String? = null
        arguments?.let { link = it.getString(HomeFragment.LINK_KEY)}
        Log.d("MyLog","linkForWebView:$link")
        val webView = binding.webview
        link = "https://www.youtube.com/"
        //webView.loadUrl(link.toString())
        webView.loadUrl("https://google.com");
        val webViewClient: WebViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            @TargetApi(Build.VERSION_CODES.N)
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                view.loadUrl(request.url.toString())
                return true
            }
        }
        webView.setWebViewClient(webViewClient);
        webView.getSettings().setJavaScriptEnabled(true);*/
        return binding.root
        //return binding.root
    }

}