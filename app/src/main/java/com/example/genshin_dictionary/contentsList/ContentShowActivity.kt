package com.example.genshin_dictionary.contentsList

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.example.genshin_dictionary.R
import com.example.genshin_dictionary.databinding.ActivityContentShowBinding
import com.example.genshin_dictionary.databinding.ActivityIntroBinding

class ContentShowActivity : AppCompatActivity() {

    private lateinit var binding : ActivityContentShowBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContentShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getUrl = intent.getStringExtra("url")

        val webView : WebView = binding.webView
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.loadUrl(getUrl.toString())
    }
}