package com.example.househeroesv2

import android.content.Intent
import android.widget.Toast
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DeleteAccountActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_account)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val confirmDeleteButton: Button = findViewById(R.id.confirm_delete_button)
        val cancelDeleteButton: Button = findViewById(R.id.cancel_delete_button)

        confirmDeleteButton.setOnClickListener {
            deleteAccount()
        }
        cancelDeleteButton.setOnClickListener {
            finish()
        }
    }

    private fun deleteAccount() {
        val user = auth.currentUser

        if (user != null) {
            val userId = user.uid

            // Delete user data from Firestore
            db.collection("Users").document(userId).delete()
                .addOnSuccessListener {
                    // Delete user from Firebase Authentication
                    user.delete()
                        .addOnSuccessListener {
                            Toast.makeText(this, "Account deleted successfully!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, SignupActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "No user is logged in.", Toast.LENGTH_SHORT).show()
        }
    }
}