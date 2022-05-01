package com.example.genshin_dictionary.character

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.genshin_dictionary.R
import com.example.genshin_dictionary.databinding.FragmentWaterBinding
import com.example.genshin_dictionary.databinding.FragmentWindBinding

class WindFragment : Fragment() {

    private lateinit var binding: FragmentWindBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWindBinding.inflate(inflater, container, false)

        binding.venti.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(
                WebView.EXTRA_URL,
                "https://namu.wiki/w/%EB%B2%A4%ED%8B%B0")
            startActivity(intent)

        }

        binding.xiao.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(
                WebView.EXTRA_URL,
                "https://namu.wiki/w/%EC%86%8C(%EC%9B%90%EC%8B%A0)"
            )
            startActivity(intent)

        }

        binding.zhin.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(
                WebView.EXTRA_URL,
                "https://namu.wiki/w/%EC%A7%84(%EC%9B%90%EC%8B%A0)"
            )
            startActivity(intent)

        }

        binding.kazuha.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(
                WebView.EXTRA_URL,
                "https://namu.wiki/w/%EC%B9%B4%EC%97%90%EB%8D%B0%ED%95%98%EB%9D%BC%20%EC%B9%B4%EC%A6%88%ED%95%98"
            )
            startActivity(intent)

        }

        binding.sayu.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(
                WebView.EXTRA_URL,
                "https://namu.wiki/w/%EC%82%AC%EC%9C%A0(%EC%9B%90%EC%8B%A0)"
            )
            startActivity(intent)

        }

        binding.sugar.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EC%84%A4%ED%83%95(%EC%9B%90%EC%8B%A0)")
            startActivity(intent)

        }

        return binding.root

    }
}