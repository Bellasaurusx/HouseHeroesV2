package com.example.househeroesv2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                // Navigate to Portal if the user is logged in
                startActivity(Intent(this, PortalActivity::class.java))
            } else {
                // Navigate to Login screen
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        }, 2000) //
    }
}