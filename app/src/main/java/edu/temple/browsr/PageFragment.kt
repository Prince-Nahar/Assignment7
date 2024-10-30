package edu.temple.browsr

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment

class PageFragment : Fragment() {

    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_page, container, false)
        webView = view.findViewById(R.id.webView)

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        return view
    }

    fun getWebView(): WebView {
        return webView
    }
}
