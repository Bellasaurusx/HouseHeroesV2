package com.example.househeroesv2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ChangePasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

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

            // Handle password change logic here
            // TODO: Add real password update logic
            Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ParentDashboardScreen::class.java)
            startActivity(intent)
            finish()
        }

        cancelButton.setOnClickListener {

            val intent = Intent(this, ParentDashboardScreen::class.java)
            startActivity(intent)
            finish()
        }
    }
}