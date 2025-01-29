package com.example.househeroesv2

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val tagline: TextView = findViewById(R.id.tagline)
        val logoImage: ImageView = findViewById(R.id.logoImage)
        val progressBar: ProgressBar = findViewById(R.id.loadingBar)
        val loadingText: TextView = findViewById(R.id.loadingText)
        val screenWidth = resources.displayMetrics.widthPixels

        // Fade-in animation for logo
        logoImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in))

        // Tagline animation: enter from left, pause, exit to right
        val enterAnimation = ObjectAnimator.ofFloat(tagline, "translationX", -screenWidth.toFloat(), 0f)
        enterAnimation.duration = 2000

        val pauseAnimation = ObjectAnimator.ofFloat(tagline, "alpha", 1f, 1f)
        pauseAnimation.duration = 1000

        val exitAnimation = ObjectAnimator.ofFloat(tagline, "translationX", 0f, screenWidth.toFloat())
        exitAnimation.duration = 2000

        val taglineAnimatorSet = AnimatorSet()
        taglineAnimatorSet.playSequentially(enterAnimation, pauseAnimation, exitAnimation)
        taglineAnimatorSet.start()


        val progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100)
        progressAnimator.duration = 4000 // 4 seconds for the progress
        progressAnimator.start()

        // Progress bar and loading text
        val handler = Handler(Looper.getMainLooper())
        var progress = 0
        val delay = 30L // Milliseconds for each increment

        val progressRunnable = object : Runnable {
            override fun run() {
                if (progress <= 100) {
                    progressBar.progress = progress
                    loadingText.text = "$progress%"
                    progress++
                    handler.postDelayed(this, 30)
                }
            }
        }
        handler.post(progressRunnable)

        // Transition to the next screen
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
                            // User doesn't exist in Firestore, navigate to login
                            auth.signOut() // Ensure user is signed out
                            startActivity(Intent(this, LoginActivity::class.java))
                        }
                        finish()
                    }
            } else {
                // No user is logged in, navigate to login screen
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }, 4000) // Total delay (4 seconds)
    }

}
