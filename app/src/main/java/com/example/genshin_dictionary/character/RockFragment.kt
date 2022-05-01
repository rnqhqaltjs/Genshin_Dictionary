package com.example.genshin_dictionary.character

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.genshin_dictionary.R
import com.example.genshin_dictionary.databinding.FragmentFireBinding
import com.example.genshin_dictionary.databinding.FragmentHeroBinding
import com.example.genshin_dictionary.databinding.FragmentRockBinding

class RockFragment : Fragment() {

    private lateinit var binding: FragmentRockBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRockBinding.inflate(inflater,container,false)

        binding.itto.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EC%95%84%EB%9D%BC%ED%83%80%ED%82%A4%20%EC%9D%B4%ED%86%A0")
            startActivity(intent)

        }

        binding.albedo.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EC%95%8C%EB%B2%A0%EB%8F%84(%EC%9B%90%EC%8B%A0)")
            startActivity(intent)

        }

        binding.zhongli.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EC%A2%85%EB%A0%A4")
            startActivity(intent)

        }

        binding.gorou.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EA%B3%A0%EB%A1%9C(%EC%9B%90%EC%8B%A0)")
            startActivity(intent)

        }

        binding.noelle.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EB%85%B8%EC%97%98(%EC%9B%90%EC%8B%A0)")
            startActivity(intent)

        }

        binding.yunjin.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EC%9A%B4%EA%B7%BC")
            startActivity(intent)

        }

        binding.ningguang.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EC%9D%91%EA%B4%91")
            startActivity(intent)

        }

        return binding.root

    }


}