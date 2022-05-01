package com.example.genshin_dictionary.contentsList

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.example.genshin_dictionary.R
import com.example.genshin_dictionary.databinding.ActivityContentShowBinding
import com.example.genshin_dictionary.databinding.ActivityIntroBinding

class ContentShowActivity : AppCompatActivity() {

    private lateinit var binding : ActivityContentShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContentShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getUrl = intent.getStringExtra("url")

        val webView : WebView = binding.webView
        webView.loadUrl(getUrl.toString())
    }
}