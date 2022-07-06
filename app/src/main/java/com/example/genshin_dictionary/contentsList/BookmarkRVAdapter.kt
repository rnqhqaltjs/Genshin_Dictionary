package com.example.genshin_dictionary.contentsList

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.genshin_dictionary.MainActivity
import com.example.genshin_dictionary.R
import com.example.genshin_dictionary.databinding.ContentRvItemBinding
import com.example.genshin_dictionary.fragments.HomeFragment
import com.example.genshin_dictionary.utils.FBAuth
import com.example.genshin_dictionary.utils.FBRef

class BookmarkRVAdapter(val context: Context,
                       val items:ArrayList<ContentModel>,
                       val keyList:ArrayList<String>,
                       val bookmarkIdList:MutableList<String>)
    : RecyclerView.Adapter<BookmarkRVAdapter.Viewholder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding = ContentRvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        Log.d("ContentRVAdapter", keyList.toString())
        Log.d("ContentRVAdapter", bookmarkIdList.toString())
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        holder.bindItems(items[position],keyList[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class Viewholder(private val binding: ContentRvItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindItems(item: ContentModel, key: String){

            itemView.setOnClickListener {
                Toast.makeText(context,item.title, Toast.LENGTH_SHORT).show()
                val intent = Intent(context, ContentShowActivity::class.java)
                intent.putExtra("url",item.webUrl)
                itemView.context.startActivity(intent)
            }

            val contentTitle = binding.textArea
            val imageViewArea = binding.imageArea
            val bookmarkArea = binding.bookmarkArea

            if(bookmarkIdList.contains(key)){
                bookmarkArea.setImageResource(R.drawable.bookmark_color)
            }else{
                bookmarkArea.setImageResource(R.drawable.bookmark_white)
            }

            bookmarkArea.setOnClickListener {

                if(bookmarkIdList.contains(key)) {


                    val builder = AlertDialog.Builder(context)
                    builder.setTitle("알림")
                    builder.setMessage("북마크를 삭제하시겠습니까?")

                    builder.setNegativeButton("아니오") { dialog, which ->

                    }

                    builder.setPositiveButton("네") { dialog, which ->

                        //북마크가 있을때 삭제
                        Toast.makeText(context,"북마크 삭제 완료",Toast.LENGTH_SHORT).show()

                        FBRef.bookmarkRef
                            .child(FBAuth.getUid())
                            .child(key)
                            .removeValue()

                        val intent = (context as Activity).intent
                        context.finish() //현재 액티비티 종료 실시

                        context.overridePendingTransition(0, 0) //효과 없애기

                        context.startActivity(intent) //현재 액티비티 재실행 실시

                        context.overridePendingTransition(0, 0) //효과 없애기


                    }


                    builder.show()

                }



            }


            contentTitle.text =item.title

            Glide.with(context)
                .load(item.imageUrl)
                .into(imageViewArea)

        }

    }
}
