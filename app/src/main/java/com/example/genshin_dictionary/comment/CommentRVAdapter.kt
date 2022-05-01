package com.example.genshin_dictionary.comment

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.genshin_dictionary.board.BoardInsideActivity
import com.example.genshin_dictionary.databinding.CommentRvItemBinding
import com.example.genshin_dictionary.utils.FBAuth
import com.example.genshin_dictionary.utils.FBRef
import kotlin.coroutines.coroutineContext

class CommentRVAdapter(val context: Context, val items:MutableList<CommentModel>,val keys:MutableList<String>)
    : RecyclerView.Adapter<CommentRVAdapter.Viewholder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding = CommentRvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        holder.bindItems(items[position],keys[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class Viewholder(private val binding: CommentRvItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindItems(item: CommentModel,key:String){

            binding.titleArea.text = items[position].commentTitle
            binding.timeArea.text = items[position].commentTime
            binding.uidArea.text = items[position].commentUid

            val myUid = FBAuth.getUid()
            val writerUid = items[position].commentUid

            if(myUid.equals(writerUid)){

                binding.deleteIcon.isVisible = true

            } else{

            }

            binding.deleteIcon.setOnClickListener {

                Toast.makeText(context,keys[position], Toast.LENGTH_SHORT).show()

                val builder = AlertDialog.Builder(context)
                builder.setTitle("알림")
                builder.setMessage("댓글을 삭제하시겠습니까?")

                builder.setNegativeButton(android.R.string.no) { dialog, which ->

                }

                builder.setPositiveButton(android.R.string.yes) { dialog, which ->

                    FBRef.commentRef.child(keys[position]).removeValue()
                    Toast.makeText(context,"댓글 삭제 완료", Toast.LENGTH_SHORT).show()
                    (context as Activity).finish()
                }

                builder.show()

            }

        }

    }

}