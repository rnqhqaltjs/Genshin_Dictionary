package com.example.genshin_dictionary.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.genshin_dictionary.R
import com.example.genshin_dictionary.contentsList.BookmarkRVAdapter
import com.example.genshin_dictionary.contentsList.ContentModel
import com.example.genshin_dictionary.databinding.FragmentBookmarkBinding
import com.example.genshin_dictionary.databinding.FragmentCharBinding
import com.example.genshin_dictionary.utils.FBAuth
import com.example.genshin_dictionary.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.concurrent.CopyOnWriteArrayList

class BookmarkFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkBinding

    private val TAG = BookmarkFragment::class.java.simpleName

    val bookmarkIdList = mutableListOf<String>()
    val items = ArrayList<ContentModel>()
    val itemKeyList = ArrayList<String>()

    lateinit var rvAdapter: BookmarkRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarkBinding.inflate(inflater,container,false)

        (activity as AppCompatActivity).supportActionBar?.title = "북마크"

        //2. 사용자가 북마크한 정보를 다 가져옴!
        getBookmarkData()

        rvAdapter = BookmarkRVAdapter(requireContext(),items,itemKeyList,bookmarkIdList)

        binding.bookmarkRV.adapter=rvAdapter
        binding.bookmarkRV.layoutManager= GridLayoutManager(requireContext(),2)




        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookmarkFragment_to_homeFragment)
        }

        binding.charTap.setOnClickListener{
            it.findNavController().navigate(R.id.action_bookmarkFragment_to_charFragment)

        }

        binding.infoTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookmarkFragment_to_infoFragment)

        }

        binding.comTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookmarkFragment_to_comFragment)

        }

        binding.bookmarkTap.setOnClickListener {

        }

        binding.shopTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookmarkFragment_to_storeFragment)
        }
        return binding.root
    }

    private fun getCategoryData(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for(dataModel in dataSnapshot.children) {
                    Log.d(TAG, dataModel.toString())
                    dataModel.getValue(ContentModel::class.java)
                    val item = dataModel.getValue(ContentModel::class.java)
                    //3. 전체 컨텐츠 중에서, 사용자가 북마크한 정보만 보여줌!
                    if(bookmarkIdList.contains(dataModel.key.toString())) {
                        items.add(item!!)
                        itemKeyList.add(dataModel.key.toString())
                    }

                }
                rvAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.category_all.addValueEventListener(postListener)
        FBRef.category_newbie.addValueEventListener(postListener)
        FBRef.category_battle.addValueEventListener(postListener)
        FBRef.category_quest.addValueEventListener(postListener)
        FBRef.category_farm.addValueEventListener(postListener)
        FBRef.category_arc.addValueEventListener(postListener)
        FBRef.category_tip.addValueEventListener(postListener)
        FBRef.category_etc.addValueEventListener(postListener)


    }
    private fun getBookmarkData(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for(dataModel in dataSnapshot.children) {
                    Log.e(TAG,dataModel.toString())
                    bookmarkIdList.add(dataModel.key.toString())

                }

                //1.전체 카테고리에 있는 컨텐츠 데이터들을 다 가져옴!
                getCategoryData()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.bookmarkRef.child(FBAuth.getUid()).addValueEventListener(postListener)
    }

}