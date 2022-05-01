package com.example.genshin_dictionary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.genshin_dictionary.auth.IntroActivity
import com.example.genshin_dictionary.databinding.ActivityMainBinding
import com.example.genshin_dictionary.databinding.ActivitySplashBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(auth.currentUser?.uid ==null){
            Log.d("SplashActivity","null")

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, IntroActivity::class.java)
                startActivity(intent)

                finish()
            },3000)


        } else{
            Log.d("SplashActivity","not null")

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                finish()
            },3000)
        }

    }
}