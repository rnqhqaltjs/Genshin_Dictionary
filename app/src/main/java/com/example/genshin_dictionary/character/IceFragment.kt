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
import com.example.genshin_dictionary.databinding.FragmentIceBinding

class IceFragment : Fragment() {

    private lateinit var binding: FragmentIceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIceBinding.inflate(inflater,container,false)

        binding.gamyu.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EA%B0%90%EC%9A%B0")
            startActivity(intent)

        }

        binding.shenhe.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EC%8B%A0%ED%95%99(%EC%9B%90%EC%8B%A0)")
            startActivity(intent)

        }

        binding.eula.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EC%9C%A0%EB%9D%BC(%EC%9B%90%EC%8B%A0)")
            startActivity(intent)

        }

        binding.aloy.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EC%97%90%EC%9D%BC%EB%A1%9C%EC%9D%B4(%EC%9B%90%EC%8B%A0)")
            startActivity(intent)

        }

        binding.qiqi.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EC%B9%98%EC%B9%98(%EC%9B%90%EC%8B%A0)")
            startActivity(intent)

        }

        binding.ayaka.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EC%B9%B4%EB%AF%B8%EC%82%AC%ED%86%A0%20%EC%95%84%EC%95%BC%EC%B9%B4")
            startActivity(intent)

        }

        binding.diona.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EB%94%94%EC%98%A4%EB%82%98")
            startActivity(intent)

        }

        binding.rosaria.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EB%A1%9C%EC%9E%90%EB%A6%AC%EC%95%84(%EC%9B%90%EC%8B%A0)")
            startActivity(intent)

        }

        binding.chongyun.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EC%A4%91%EC%9A%B4")
            startActivity(intent)

        }

        binding.kaeya.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EC%BC%80%EC%9D%B4%EC%95%84")
            startActivity(intent)

        }

        return binding.root
    }


}