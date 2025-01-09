package com.example.househeroesv2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ParentDashboardScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_dashboard)

        val profilePicture: ImageView = findViewById(R.id.profile_picture)
        val userName: TextView = findViewById(R.id.user_name)

        userName.text = getString(R.string.parent_name)
        profilePicture.setImageResource(R.drawable.default_profile_picture)

        val addChoreButton: Button = findViewById(R.id.add_chore_button)
        addChoreButton.setOnClickListener {
            val intent = Intent(this, AddChoreActivity::class.java)
            startActivity(intent)
        }

        val addChildButton: Button = findViewById(R.id.add_child_button)
        addChildButton.setOnClickListener {
            val intent = Intent(this, AddChildActivity::class.java)
            startActivity(intent)
        }

        val changePasswordButton: Button = findViewById(R.id.change_password_button)
        changePasswordButton.setOnClickListener {
            val intent = Intent(this, ChangePasswordActivity::class.java)
            startActivity(intent)
        }

        val deleteAccountButton: Button = findViewById(R.id.delete_account_button)
        deleteAccountButton.setOnClickListener {
            val intent = Intent(this, DeleteAccountActivity::class.java)
            startActivity(intent)
        }
    }
}