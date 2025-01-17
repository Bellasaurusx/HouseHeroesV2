package com.example.househeroesv2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        auth = FirebaseAuth.getInstance()

        val oldPasswordInput = findViewById<EditText>(R.id.old_password)
        val newPasswordInput = findViewById<EditText>(R.id.new_password)
        val confirmPasswordInput = findViewById<EditText>(R.id.confirm_password)
        val saveButton = findViewById<Button>(R.id.save_password_button)
        val cancelButton = findViewById<Button>(R.id.cancel_button)

        saveButton.setOnClickListener {
            val oldPassword = oldPasswordInput.text.toString()
            val newPassword = newPasswordInput.text.toString()
            val confirmPassword = confirmPasswordInput.text.toString()

            if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (newPassword != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            updatePassword(oldPassword, newPassword)
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun updatePassword(oldPassword: String, newPassword: String) {
        val user = auth.currentUser

        if (user != null) {
            val email = user.email
            if (!email.isNullOrEmpty()) {
                val credential = EmailAuthProvider.getCredential(email, oldPassword)

                // Reauthenticate
                user.reauthenticate(credential)
                    .addOnSuccessListener {
                        // Update password
                        user.updatePassword(newPassword)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Password updated successfully!", Toast.LENGTH_SHORT).show()
                                finish()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Reauthentication failed: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}