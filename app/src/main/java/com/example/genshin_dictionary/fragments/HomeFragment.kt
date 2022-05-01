package com.example.genshin_dictionary.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.genshin_dictionary.R
import com.example.genshin_dictionary.databinding.ActivityMainBinding
import com.example.genshin_dictionary.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater,container,false)

        (activity as AppCompatActivity).supportActionBar?.title = "홈"



        binding.charTap.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_charFragment)

        }

        binding.infoTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_infoFragment)

        }

        binding.comTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_comFragment)

        }

        binding.bookmarkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_bookmarkFragment)

        }

        binding.shopTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_storeFragment)

        }

        return binding.root
    }

}
