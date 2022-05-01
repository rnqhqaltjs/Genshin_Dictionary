package com.example.genshin_dictionary.character

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.genshin_dictionary.R
import com.example.genshin_dictionary.databinding.FragmentFireBinding
import com.example.genshin_dictionary.databinding.FragmentWaterBinding

class WaterFragment : Fragment() {

    private lateinit var binding: FragmentWaterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWaterBinding.inflate(inflater, container, false)

        binding.mona.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EB%AA%A8%EB%82%98(%EC%9B%90%EC%8B%A0)")
            startActivity(intent)

        }

        binding.kokomi.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(
                WebView.EXTRA_URL,
                "https://namu.wiki/w/%EC%82%B0%EA%B3%A0%EB%85%B8%EB%AF%B8%EC%95%BC%20%EC%BD%94%EC%BD%94%EB%AF%B8"
            )
            startActivity(intent)

        }

        binding.ayato.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(
                WebView.EXTRA_URL,
                "https://namu.wiki/w/%EC%B9%B4%EB%AF%B8%EC%82%AC%ED%86%A0%20%EC%95%84%EC%95%BC%ED%86%A0"
            )
            startActivity(intent)

        }

        binding.tattalia.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(
                WebView.EXTRA_URL,
                "https://namu.wiki/w/%ED%83%80%EB%A5%B4%ED%83%88%EB%A6%AC%EC%95%84"
            )
            startActivity(intent)

        }

        binding.babara.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(
                WebView.EXTRA_URL,
                "https://namu.wiki/w/%EB%B0%94%EB%B0%94%EB%9D%BC(%EC%9B%90%EC%8B%A0)"
            )
            startActivity(intent)

        }

        binding.xingqiu.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%ED%96%89%EC%B6%94")
            startActivity(intent)

        }

        return binding.root

    }
}