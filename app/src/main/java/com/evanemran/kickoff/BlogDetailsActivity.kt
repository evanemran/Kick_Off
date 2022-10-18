package com.evanemran.kickoff

import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.Circle
import kotlinx.android.synthetic.main.activity_blog_details.*
import kotlinx.android.synthetic.main.fragment_history.*


class BlogDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog_details)

        val animation: Sprite = Circle()
        spin_kit_blog.setIndeterminateDrawable(animation)

        val url: String? = intent.getStringExtra("url")
        blog_webView.loadUrl(url!!)

        blog_webView.setWebViewClient(object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                if (blog_webView.visibility == View.GONE) {
                    blog_webView.visibility = View.VISIBLE
                    spin_kit_blog.visibility = View.GONE
                }
            }
        })

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