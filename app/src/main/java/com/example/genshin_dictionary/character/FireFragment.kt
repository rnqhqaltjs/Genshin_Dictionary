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

class FireFragment : Fragment() {

    private lateinit var binding: FragmentFireBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFireBinding.inflate(inflater,container,false)

        binding.klee.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%ED%81%B4%EB%A0%88")
            startActivity(intent)

        }

        binding.dyruk.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EB%8B%A4%EC%9D%B4%EB%A3%A8%ED%81%AC")
            startActivity(intent)

        }

        binding.yoimiya.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EC%9A%94%EC%9D%B4%EB%AF%B8%EC%95%BC")
            startActivity(intent)

        }

        binding.hutao.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%ED%98%B8%EB%91%90(%EC%9B%90%EC%8B%A0)")
            startActivity(intent)

        }

        binding.benet.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EB%B2%A0%EB%84%B7")
            startActivity(intent)

        }

        binding.xinyan.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EC%8B%A0%EC%97%BC")
            startActivity(intent)

        }

        binding.amber.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EC%97%A0%EB%B2%84(%EC%9B%90%EC%8B%A0)")
            startActivity(intent)

        }

        binding.yanfei.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EC%97%B0%EB%B9%84(%EC%9B%90%EC%8B%A0)")
            startActivity(intent)

        }

        binding.toma.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%ED%86%A0%EB%A7%88(%EC%9B%90%EC%8B%A0)")
            startActivity(intent)

        }

        binding.xiangling.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%ED%96%A5%EB%A6%89(%EC%9B%90%EC%8B%A0)")
            startActivity(intent)

        }

        return binding.root
    }


}