package com.example.househeroesv2

import android.content.Intent
import android.widget.Toast
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class DeleteAccountActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_account)

        auth = FirebaseAuth.getInstance()

        val confirmDeleteButton: Button = findViewById(R.id.confirm_delete_button)
        val cancelDeleteButton: Button = findViewById(R.id.cancel_delete_button)

        confirmDeleteButton.setOnClickListener {
            deleteAccount()
        }
        cancelDeleteButton.setOnClickListener {
            val intent = Intent(this, ParentDashboardScreen::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun deleteAccount() {
        val user = auth.currentUser
        if (user != null) {
            user.delete()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Account deleted successfully", Toast.LENGTH_SHORT).show()
                        navigateToLogin()
                    } else {
                        Toast.makeText(this, "Failed to delete account: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this, "No user logged in", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Clear activity stack
        startActivity(intent)
        finish()
    }
}