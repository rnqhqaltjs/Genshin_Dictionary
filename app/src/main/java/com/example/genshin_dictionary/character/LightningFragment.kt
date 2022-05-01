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
import com.example.genshin_dictionary.databinding.FragmentLightningBinding

class LightningFragment : Fragment() {

    private lateinit var binding: FragmentLightningBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLightningBinding.inflate(inflater,container,false)

        binding.qeching.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EA%B0%81%EC%B2%AD")
            startActivity(intent)

        }

        binding.raiden.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EB%9D%BC%EC%9D%B4%EB%8D%B4%20%EC%87%BC%EA%B5%B0")
            startActivity(intent)

        }

        binding.miko.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EC%95%BC%EC%97%90%20%EB%AF%B8%EC%BD%94")
            startActivity(intent)

        }

        binding.risa.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EB%A6%AC%EC%82%AC(%EC%9B%90%EC%8B%A0)")
            startActivity(intent)

        }

        binding.razer.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EB%A0%88%EC%9D%B4%EC%A0%80(%EC%9B%90%EC%8B%A0)")
            startActivity(intent)

        }

        binding.beidou.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EB%B6%81%EB%91%90(%EC%9B%90%EC%8B%A0)")
            startActivity(intent)

        }

        binding.fishl.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%ED%94%BC%EC%8A%AC")
            startActivity(intent)

        }

        binding.sara.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EC%BF%A0%EC%A3%A0%20%EC%82%AC%EB%9D%BC")
            startActivity(intent)

        }

        return binding.root
    }


}