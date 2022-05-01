package com.example.genshin_dictionary.utils

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FBRef {
    companion object {

        private val database = Firebase.database

        val category_all = database.getReference("contents_all")
        val category_newbie = database.getReference("contents_newbie")
        val category_battle = database.getReference("contents_battle")
        val category_quest = database.getReference("contents_quest")
        val category_farm = database.getReference("contents_farm")
        val category_arc = database.getReference("contents_arc")
        val category_tip = database.getReference("contents_tip")
        val category_etc = database.getReference("contents_etc")

        val bookmarkRef = database.getReference("bookmark_list")

        val boardRef = database.getReference("board")

        val commentRef = database.getReference("comment")


    }
}