package com.example.househeroesv2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.househeroesv2.databinding.ActivityPortalBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PortalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPortalBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPortalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        checkAuthentication()
    }
    private fun checkAuthentication() {
        val currentUser = auth.currentUser
        if (currentUser == null) {
            navigateToLogin()
        } else {
            fetchUserRole()
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Clear activity stack
        startActivity(intent)
        finish()
    }

    private fun fetchUserRole() {
        val currentUser = auth.currentUser

        if (currentUser != null) {
            val userId = currentUser.uid

            db.collection("Users").document(userId).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val role = document.getString("role")
                        val name = document.getString("name")

                        Toast.makeText(
                            this,
                            "Welcome, $name! Role: $role",
                            Toast.LENGTH_LONG
                        ).show()

                        setupButtonsBasedOnRole(role)
                    } else {
                        Toast.makeText(
                            this,
                            "User data not found. Please contact support.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(
                        this,
                        "Error retrieving user data: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        } else {
            Toast.makeText(this, "No user is logged in.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupButtonsBasedOnRole(role: String?) {
        when (role) {
            "parent" -> {
                val currentUser = auth.currentUser
                if (currentUser != null) {
                    val userId = currentUser.uid

                    db.collection("Users").document(userId).get()
                        .addOnSuccessListener { document ->
                            if (document != null && document.exists()) {
                                val passcode = document.getString("passcode")

                                if (passcode.isNullOrEmpty()) {
                                    val intent = Intent(this, SetPasscodeActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    binding.parentPortalButton.setOnClickListener {
                                        val intent = Intent(this, ParentPasscodeActivity::class.java)
                                        startActivity(intent)
                                    }
                                }
                            }
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                }
            }
            "child" -> {
                binding.childPortalButton.setOnClickListener {
                    val intent = Intent(this, ChildActivity::class.java)
                    startActivity(intent)
                }

                binding.parentPortalButton.isEnabled = false
                binding.parentPortalButton.alpha = 0.5f
            }
            else -> {
                Toast.makeText(this, "Unknown role. Please contact support.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

