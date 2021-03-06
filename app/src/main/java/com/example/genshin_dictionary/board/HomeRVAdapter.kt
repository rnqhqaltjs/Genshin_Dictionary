package com.example.genshin_dictionary.board

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.genshin_dictionary.databinding.HomeRvItemBinding
import com.example.genshin_dictionary.utils.FBAuth

class HomeRVAdapter(val context: Context,val items:MutableList<BoardModel>,val keys:MutableList<String>)
    : RecyclerView.Adapter<HomeRVAdapter.Viewholder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding = HomeRvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        holder.bindItems()
    }

    override fun getItemCount(): Int {

        return items.size.coerceAtMost(8)
    }

    inner class Viewholder(private val binding: HomeRvItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindItems() {

            binding.titleArea.text = items[position].title
            binding.timeArea.text = items[position].time
            binding.emailArea.text = items[position].email

            if(binding.emailArea.text == ""){

                binding.emailArea.text = "익명"

            }

            val itemLinearLayoutView = binding.BoardListView

            if(items[position].uid.equals(FBAuth.getUid())){
                itemLinearLayoutView.setBackgroundColor(Color.parseColor("#d3d3d3"))
            }

            binding.BoardListView.setOnClickListener  {

                val intent = Intent(context, BoardInsideActivity::class.java)
                intent.putExtra("key",keys[position])
                binding.BoardListView.context.startActivity(intent)

            }




        }

    }
}