package com.example.genshin_dictionary.character

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.genshin_dictionary.databinding.FragmentHeroBinding

class HeroFragment : Fragment() {

    private lateinit var binding: FragmentHeroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeroBinding.inflate(inflater,container,false)

        binding.aither.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EC%97%AC%ED%96%89%EC%9E%90(%EC%9B%90%EC%8B%A0)")
            startActivity(intent)

        }

        binding.lumine.setOnClickListener {

            val intent = Intent(context, WebView::class.java)
            intent.putExtra(WebView.EXTRA_URL, "https://namu.wiki/w/%EC%97%AC%ED%96%89%EC%9E%90(%EC%9B%90%EC%8B%A0)")
            startActivity(intent)

        }

        return binding.root
    }

}