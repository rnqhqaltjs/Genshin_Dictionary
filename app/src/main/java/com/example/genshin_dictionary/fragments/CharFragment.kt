package com.example.genshin_dictionary.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.genshin_dictionary.R
import com.example.genshin_dictionary.character.*
import com.example.genshin_dictionary.databinding.FragmentCharBinding
import com.example.genshin_dictionary.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class CharFragment : Fragment() {

    private lateinit var binding: FragmentCharBinding

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharBinding.inflate(inflater,container,false)

        viewPager = binding.pager
        tabLayout = binding.tabLayout

        (activity as AppCompatActivity).supportActionBar?.title = "캐릭터 정보"

        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_charFragment_to_homeFragment)
        }

        binding.infoTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_charFragment_to_infoFragment)

        }

        binding.comTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_charFragment_to_comFragment)

        }

        binding.bookmarkTap.setOnClickListener{
            it.findNavController().navigate(R.id.action_charFragment_to_bookmarkFragment)

        }

        binding.shopTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_charFragment_to_storeFragment)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pagerAdapter = PagerFragmentStateAdapter(requireActivity())

        pagerAdapter.addFragment(HeroFragment())
        pagerAdapter.addFragment(FireFragment())
        pagerAdapter.addFragment(WaterFragment())
        pagerAdapter.addFragment(WindFragment())
        pagerAdapter.addFragment(LightningFragment())
        pagerAdapter.addFragment(IceFragment())
        pagerAdapter.addFragment(RockFragment())

        viewPager.adapter = pagerAdapter
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int){
                super.onPageSelected(position)
                Log.e("ViewPagerFragment", "Page ${position+1}")
            }
        })

        val tabTextArray = arrayOf("행자","불","물","바람","번개","얼음","바위")
        val tabLayoutIconArray = arrayOf(R.drawable.hero,R.drawable.fire,R.drawable.water,R.drawable.wind,R.drawable.lightning,R.drawable.ice,R.drawable.rock)

        TabLayoutMediator(tabLayout, viewPager){ tab, position ->
            tab.text = tabTextArray[position]
            tab.setIcon(tabLayoutIconArray[position])
        }.attach()

    }

}