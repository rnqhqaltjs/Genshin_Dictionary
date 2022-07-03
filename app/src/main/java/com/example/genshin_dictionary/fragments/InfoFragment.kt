package com.example.genshin_dictionary.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.findNavController
import com.example.genshin_dictionary.R
import com.example.genshin_dictionary.contentsList.ContentListActivity
import com.example.genshin_dictionary.databinding.FragmentInfoBinding
import com.example.genshin_dictionary.databinding.FragmentStoreBinding


class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding.inflate(inflater,container,false)

        (activity as AppCompatActivity).supportActionBar?.title = "팁과 정보"

        binding.mainIconAll.setOnClickListener {

            val intent = Intent(context , ContentListActivity::class.java)
            intent.putExtra("category","category_all")
            startActivity(intent)
        }

        binding.mainIconNewbie.setOnClickListener {
            val intent = Intent(context , ContentListActivity::class.java)
            intent.putExtra("category","category_newbie")
            startActivity(intent)
        }

        binding.mainIconBattle.setOnClickListener {
            val intent = Intent(context , ContentListActivity::class.java)
            intent.putExtra("category","category_battle")
            startActivity(intent)
        }

        binding.mainIconQuest.setOnClickListener {
            val intent = Intent(context , ContentListActivity::class.java)
            intent.putExtra("category","category_quest")
            startActivity(intent)
        }

        binding.mainIconFarm.setOnClickListener {
            val intent = Intent(context , ContentListActivity::class.java)
            intent.putExtra("category","category_farm")
            startActivity(intent)
        }

        binding.mainIconArc.setOnClickListener {
            val intent = Intent(context , ContentListActivity::class.java)
            intent.putExtra("category","category_arc")
            startActivity(intent)
        }

        binding.mainIconTip.setOnClickListener {
            val intent = Intent(context , ContentListActivity::class.java)
            intent.putExtra("category","category_tip")
            startActivity(intent)
        }

        binding.mainIconEtc.setOnClickListener {
            val intent = Intent(context , ContentListActivity::class.java)
            intent.putExtra("category","category_etc")
            startActivity(intent)
        }



        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_infoFragment_to_homeFragment)
        }

        binding.charTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_infoFragment_to_charFragment)

        }

        binding.infoTap.setOnClickListener {

        }

        binding.comTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_infoFragment_to_comFragment)

        }

        binding.bookmarkTap.setOnClickListener{
            it.findNavController().navigate(R.id.action_infoFragment_to_bookmarkFragment)

        }

        binding.shopTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_infoFragment_to_storeFragment)
        }
        return binding.root
    }



}

