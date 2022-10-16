package com.evanemran.kickoff

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_blog_details.*

class BlogDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog_details)

        val url: String? = intent.getStringExtra("url")


        blog_webView.webViewClient = WebViewClient()
        blog_webView.loadUrl(url!!)
        val webSettings: WebSettings = blog_webView.settings
        webSettings.javaScriptEnabled = true
    }

    override fun onBackPressed() {
        if (blog_webView.canGoBack()) {
            blog_webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}