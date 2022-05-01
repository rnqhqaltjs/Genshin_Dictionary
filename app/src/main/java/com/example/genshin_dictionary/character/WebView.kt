package com.example.genshin_dictionary.character

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.genshin_dictionary.R
import com.example.genshin_dictionary.databinding.ActivityBoardWriteBinding
import com.example.genshin_dictionary.databinding.ActivityWebViewBinding
import com.example.genshin_dictionary.databinding.FragmentHeroBinding

class WebView : AppCompatActivity() {

    companion object {
        const val EXTRA_URL = "url" // 인텐트 키 상수 선언
    }

    private lateinit var binding: ActivityWebViewBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getStringExtra(EXTRA_URL)?.let { url ->
            binding.run {
                // 바인딩에서 webView 세팅
                characterWebView.settings.javaScriptEnabled = true
                characterWebView.settings.domStorageEnabled = true
                characterWebView.settings.setSupportMultipleWindows(true)
                characterWebView.webViewClient = WebViewClient() // 클릭 시 새창 뜨지 않게
                // 줌 설정
                characterWebView.settings.builtInZoomControls = true
                characterWebView.settings.setSupportZoom(true)
                characterWebView.settings.displayZoomControls = false
                // 주소 로딩
                characterWebView.loadUrl(url)
            }
        }
    }
}