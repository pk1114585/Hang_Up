package com.pk.hangup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth

class Splash : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser?.uid!=null)
        {
            Handler().postDelayed(Runnable {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            },100)
        }else
        {
            Handler().postDelayed(Runnable {
                val intent  = Intent(this,LoginServiceActivity::class.java)
                startActivity(intent)
                finish()
            },100)
        }
    }
}