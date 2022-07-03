package com.example.genshin_dictionary.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.genshin_dictionary.R
import com.example.genshin_dictionary.databinding.FragmentCharBinding
import com.example.genshin_dictionary.databinding.FragmentStoreBinding


class StoreFragment : Fragment() {

    private lateinit var binding: FragmentStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoreBinding.inflate(inflater,container,false)

        (activity as AppCompatActivity).supportActionBar?.title = "원신 공식 스토어"

        val webView : WebView = binding.storeWebView
        webView.loadUrl("file:///android_asset/슈퍼플레이 콘솔마켓.html")

        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_storeFragment_to_homeFragment)
        }

        binding.charTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_storeFragment_to_charFragment)

        }

        binding.infoTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_storeFragment_to_infoFragment)

        }

        binding.comTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_storeFragment_to_comFragment)
        }

        binding.bookmarkTap.setOnClickListener{
            it.findNavController().navigate(R.id.action_storeFragment_to_bookmarkFragment)

        }

        binding.shopTap.setOnClickListener{

        }



        return binding.root
    }

}