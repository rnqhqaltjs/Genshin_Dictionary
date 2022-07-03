package com.example.genshin_dictionary.comment

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.genshin_dictionary.databinding.CommentRvItemBinding
import com.example.genshin_dictionary.utils.FBAuth
import com.example.genshin_dictionary.utils.FBRef

class CommentRVAdapter(val context: Context, val items:MutableList<CommentModel>,val keys:MutableList<String>)
    : RecyclerView.Adapter<CommentRVAdapter.Viewholder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding = CommentRvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        holder.bindItems()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class Viewholder(private val binding: CommentRvItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindItems() {

            val boardkey = (context as Activity).intent.getStringExtra("key").toString()

            binding.titleArea.text = items[position].commentTitle
            binding.timeArea.text = items[position].commentTime
            binding.uidArea.text = items[position].commentUid

            val myUid = FBAuth.getUid()
            val writerUid = items[position].commentUid

            if(myUid == writerUid){

                binding.deleteIcon.isVisible = true

            }

            binding.deleteIcon.setOnClickListener {

                val builder = AlertDialog.Builder(context)
                builder.setTitle("알림")
                builder.setMessage("댓글을 삭제하시겠습니까?")

                builder.setNegativeButton(android.R.string.no) { dialog, which ->

                }

                builder.setPositiveButton(android.R.string.yes) { dialog, which ->

                    FBRef.commentRef.child(boardkey).child(keys[position]).removeValue()
                    Toast.makeText(context,"댓글 삭제 완료", Toast.LENGTH_SHORT).show()

                }

                builder.show()

            }

        }

    }

}