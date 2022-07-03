package com.example.genshin_dictionary.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.genshin_dictionary.R
import com.example.genshin_dictionary.board.BoardInsideActivity
import com.example.genshin_dictionary.board.BoardModel
import com.example.genshin_dictionary.board.BoardRVAdapter
import com.example.genshin_dictionary.board.BoardWriteActivity
import com.example.genshin_dictionary.databinding.FragmentComBinding
import com.example.genshin_dictionary.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class ComFragment : Fragment() {

    private lateinit var binding: FragmentComBinding

    private val boardDataList = mutableListOf<BoardModel>()
    private val boardKeyList = mutableListOf<String>()

    private val TAG = ComFragment::class.java.simpleName

    private lateinit var boardRVAdapter: BoardRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComBinding.inflate(inflater,container,false)

        (activity as AppCompatActivity).supportActionBar?.title = "커뮤니티"

        boardRVAdapter = context?.let { BoardRVAdapter(it,boardDataList,boardKeyList) }!!
        binding.BoardListView.adapter = boardRVAdapter
        binding.BoardListView.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)
        binding.BoardListView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        //첫번째 방법

        binding.writeBtn.setOnClickListener {
            val intent = Intent(context, BoardWriteActivity::class.java)
            startActivity(intent)
        }

        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_comFragment_to_homeFragment)
        }

        binding.charTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_comFragment_to_charFragment)

        }

        binding.infoTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_comFragment_to_infoFragment)

        }

        binding.comTap.setOnClickListener {

        }

        binding.bookmarkTap.setOnClickListener{
            it.findNavController().navigate(R.id.action_comFragment_to_bookmarkFragment)

        }

        binding.shopTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_comFragment_to_storeFragment)
        }

        getFBBoardData()

        return binding.root
    }

    private fun getFBBoardData(){

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