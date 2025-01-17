package com.example.househeroesv2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            val auth = FirebaseAuth.getInstance()
            val user = auth.currentUser

            if (user != null) {
                // Check if the user exists in Firestore
                FirebaseFirestore.getInstance()
                    .collection("Users")
                    .document(user.uid)
                    .get()
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful && task.result != null && task.result.exists()) {
                            // User exists in Firestore, navigate to Portal
                            startActivity(Intent(this, PortalActivity::class.java))
                        } else {
                            // User doesn't exist in Firestore, navigate to Login
                            auth.signOut() // Ensure user is signed out
                            startActivity(Intent(this, LoginActivity::class.java))
                        }
                        finish()
                    }
            } else {
                // No user is logged in, navigate to Login screen
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }, 1000) // Delay for 1 second
    }
}