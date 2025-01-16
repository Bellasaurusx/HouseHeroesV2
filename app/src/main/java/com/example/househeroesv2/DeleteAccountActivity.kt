package com.example.househeroesv2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DeleteAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_account)

        val confirmDeleteButton: Button = findViewById(R.id.confirm_delete_button)
        val cancelDeleteButton: Button = findViewById(R.id.cancel_delete_button)

        confirmDeleteButton.setOnClickListener {
            val intent = Intent(this, ParentDashboardScreen::class.java)
            startActivity(intent)
            finish()
        }

        cancelDeleteButton.setOnClickListener {

            val intent = Intent(this, ParentDashboardScreen::class.java)
            startActivity(intent)
            finish()
        }
    }
}