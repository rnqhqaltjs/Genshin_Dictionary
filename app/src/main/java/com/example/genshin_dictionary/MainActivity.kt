package com.example.genshin_dictionary

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.genshin_dictionary.auth.IntroActivity
import com.example.genshin_dictionary.character.WebView
import com.example.genshin_dictionary.databinding.ActivityMainBinding
import com.example.genshin_dictionary.utils.FBAuth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.app.ProgressDialog




class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding : ActivityMainBinding
    lateinit var toggle:ActionBarDrawerToggle
    private val TAG = MainActivity::class.java.simpleName
    private val email = FBAuth.getEmail()
    private final var FINISH_INTERVAL_TIME: Long = 2000
    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        toggle = ActionBarDrawerToggle(this,binding.drawer,R.string.drawer_opened,R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        supportActionBar?.title = "홈"

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.item_news -> {
                    val intent = Intent(this, WebView::class.java)
                    intent.putExtra(WebView.EXTRA_URL, "https://genshin.hoyoverse.com/ko/news")
                        startActivity(intent)

                }

                R.id.item_notice -> {
                    val intent = Intent(this, WebView::class.java)
                    intent.putExtra(WebView.EXTRA_URL, "https://genshin.hoyoverse.com/ko/news/126")
                    startActivity(intent)

                }

                R.id.item_event -> {
                    val intent = Intent(this, WebView::class.java)
                    intent.putExtra(WebView.EXTRA_URL, "https://genshin.hoyoverse.com/ko/news/127")
                    startActivity(intent)

                }

                R.id.item_signOut ->{
                    auth.signOut()
                    val intent = Intent(this, IntroActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)

                }

            }
            true
        }

    }

    @SuppressLint("SetTextI18n")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){

            if(email == "") {

                findViewById<TextView>(R.id.tv_name).text = "Guest 님"

            } else{

                findViewById<TextView>(R.id.tv_name).text="$email 님"

            }

            if(findViewById<TextView>(R.id.tv_name).text == "null 님"){

                findViewById<TextView>(R.id.tv_name).text = "Guest 님"

            }

            return true
        }

        return super.onOptionsItemSelected(item)

    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 0) {
            val tempTime = System.currentTimeMillis();
            val intervalTime = tempTime - backPressedTime;
            if (intervalTime in 0..FINISH_INTERVAL_TIME) {
                finish()
            } else {
                backPressedTime = tempTime;
                Toast.makeText(this, "'뒤로' 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
                return
            }
        }
        super.onBackPressed();
    }

}