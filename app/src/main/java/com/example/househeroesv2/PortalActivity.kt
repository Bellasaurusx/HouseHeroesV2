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
                // Allow parent to access child content
                binding.childPortalButton.setOnClickListener {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }

                binding.parentPortalButton.setOnClickListener {
                    val intent = Intent(this, ParentPasscodeActivity::class.java)
                    startActivity(intent)
                }

                // Ensure both buttons are fully enabled for parent
                binding.parentPortalButton.isEnabled = true
                binding.parentPortalButton.alpha = 1.0f
                binding.childPortalButton.isEnabled = true
                binding.childPortalButton.alpha = 1.0f
            }

            "child" -> {
                binding.parentPortalButton.isEnabled = false
                binding.parentPortalButton.alpha = 0.5f
                binding.childPortalButton.isEnabled = false
                binding.childPortalButton.alpha = 0.5f
                Toast.makeText(
                    this,
                    "Access restricted. Please contact your parent for access.",
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> {

                Toast.makeText(this, "Unknown role. Please contact support.", Toast.LENGTH_SHORT).show()
            }
        }
    }


}

