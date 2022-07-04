package com.example.genshin_dictionary.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.genshin_dictionary.LoadingDialog
import com.example.genshin_dictionary.R
import com.example.genshin_dictionary.board.BoardModel
import com.example.genshin_dictionary.board.HomeRVAdapter
import com.example.genshin_dictionary.contentsList.BookmarkRVAdapter
import com.example.genshin_dictionary.contentsList.ContentListActivity
import com.example.genshin_dictionary.contentsList.ContentModel
import com.example.genshin_dictionary.databinding.FragmentHomeBinding
import com.example.genshin_dictionary.utils.FBAuth
import com.example.genshin_dictionary.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.content.SharedPreferences
import android.app.Activity

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    private val TAG = HomeFragment::class.java.simpleName

    val bookmarkIdList = mutableListOf<String>()
    val items = ArrayList<ContentModel>()
    val itemKeyList = ArrayList<String>()

    val boardDataList = mutableListOf<BoardModel>()
    val boardKeyList = mutableListOf<String>()

    lateinit var rvAdapter: BookmarkRVAdapter
    lateinit var boardRVAdapter: HomeRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater,container,false)

        (activity as AppCompatActivity).supportActionBar?.title = "홈"

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

        binding.goinfo.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_infoFragment)
        }

        binding.gocom.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_comFragment)
        }

        binding.gobookmark.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_bookmarkFragment)
        }


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

        getBookmarkData()
        rvAdapter = BookmarkRVAdapter(requireContext(),items,itemKeyList,bookmarkIdList)
        binding.mainRV.adapter=rvAdapter
        binding.mainRV.layoutManager= GridLayoutManager(requireContext(),2)

        getFBBoardData()
        boardRVAdapter = context?.let { HomeRVAdapter(it,boardDataList,boardKeyList) }!!
        binding.comRV.adapter = boardRVAdapter
        binding.comRV.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.comRV.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))


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

    private fun getFBBoardData(){

        val dialog = LoadingDialog(requireContext())
        dialog.show()

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                boardDataList.clear()

                for(dataModel in dataSnapshot.children) {

                    Log.d(TAG,dataModel.toString())
                    dataModel.key

                    val item = dataModel.getValue(BoardModel::class.java)
                    boardDataList.add(item!!)
                    boardKeyList.add(dataModel.key.toString())

                }
                boardKeyList.reverse()
                boardDataList.reverse()
                boardRVAdapter.notifyDataSetChanged()
                dialog.dismiss()

                Log.d(TAG,boardDataList.toString())

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.boardRef.addValueEventListener(postListener)

    }

}
