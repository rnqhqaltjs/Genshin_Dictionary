package com.example.genshin_dictionary.board

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.genshin_dictionary.R
import com.example.genshin_dictionary.comment.CommentModel
import com.example.genshin_dictionary.comment.CommentRVAdapter
import com.example.genshin_dictionary.databinding.ActivityBoardInsideBinding
import com.example.genshin_dictionary.utils.FBAuth
import com.example.genshin_dictionary.utils.FBRef
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class BoardInsideActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBoardInsideBinding
    private lateinit var key : String
    private val TAG = BoardInsideActivity::class.java.simpleName
    private val CommentDataList = mutableListOf<CommentModel>()
    private val CommentKeyList = mutableListOf<String>()

    private lateinit var commentAdapter:CommentRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBoardInsideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.boardSettingIcon.setOnClickListener {

            showDialog()

        }

        key = intent.getStringExtra("key").toString()
        getBoardData(key)
        getImageData(key)

        binding.commentBtn.setOnClickListener {

            insertComment(key)

        }

        getCommentData(key)

        commentAdapter = CommentRVAdapter(this,CommentDataList,CommentKeyList)
        binding.commentRV.adapter = commentAdapter
        binding.commentRV.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.commentRV.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

    }

    fun getCommentData(key:String){

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                CommentDataList.clear()

                for(dataModel in dataSnapshot.children) {

                    Log.d(TAG,dataModel.toString())
                    dataModel.key

                    val item = dataModel.getValue(CommentModel::class.java)
                    CommentDataList.add(item!!)
                    CommentKeyList.add(dataModel.key.toString())

                }
                commentAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.commentRef.child(key).addValueEventListener(postListener)
    }

    fun insertComment(key :String){

        if(binding.commentArea.length() == 0) {

            Toast.makeText(this,"????????? ????????? ?????????.",Toast.LENGTH_SHORT).show()

        } else {

            var email = FBAuth.getEmail()

            if (email == "") {
                email = "??????"
            }

            FBRef
                .commentRef
                .child(key)
                .push()
                .setValue(
                    CommentModel(
                        binding.commentArea.text.toString(),
                        FBAuth.getTime(),
                        FBAuth.getUid(),
                        email
                    )
                )

            Toast.makeText(this, "?????? ??????", Toast.LENGTH_SHORT).show()

            //?????? ???????????? ????????????
            finish() //????????? ??????
            overridePendingTransition(0, 0) //????????? ?????? ?????????
            val intent = intent //?????????
            startActivity(intent) //???????????? ??????
            overridePendingTransition(0, 0) //????????? ?????? ?????????

        }

    }

    private fun showDialog(){

        val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("????????? ??????/??????")

        val alertDialog = mBuilder.show()
        alertDialog.findViewById<Button>(R.id.editBtn)?.setOnClickListener {

            val intent = Intent(this, BoardEditActivity::class.java)
            intent.putExtra("key",key)
            startActivity(intent)
            alertDialog.cancel()

        }
        alertDialog.findViewById<Button>(R.id.removeBtn)?.setOnClickListener {
            FBRef.boardRef.child(key).removeValue()
            Toast.makeText(this,"?????? ??????",Toast.LENGTH_SHORT).show()
            finish()
        }

    }

    private fun getImageData(key: String){

        // Reference to an image file in Cloud Storage
        val storageReference = Firebase.storage.reference.child(key + ".png")

        // ImageView in your Activity
        val imageViewFromFB = binding.getImageArea

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task->
            if(task.isSuccessful){
                Glide.with(this)
                    .load(task.result)
                    .into(imageViewFromFB)
            } else{

                binding.getImageArea.isVisible=false

            }
        })
    }

    private fun getBoardData(key: String) {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try {

                    val dataModel = dataSnapshot.getValue(BoardModel::class.java)
                    dataModel!!.title?.let { Log.d(TAG, it) }

                    binding.titleArea.text = dataModel.title
                    binding.textArea.text = dataModel.content
                    binding.timeArea.text = dataModel.time
                    binding.emailArea.text = dataModel.email

                    if(binding.emailArea.text == ""){

                        binding.emailArea.text = "??????"

                    }

                    val myUid = FBAuth.getUid()
                    val writerUid = dataModel.uid

                    if(myUid == writerUid){

                        binding.boardSettingIcon.isVisible = true

                    }

                } catch (e:Exception){

                    Log.d(TAG,"????????????")

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.boardRef.child(key).addValueEventListener(postListener)
    }
}
