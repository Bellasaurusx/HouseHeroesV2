package com.example.househeroesv2

import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddChoreActivity : AppCompatActivity() {

    private lateinit var choreNameEditText: EditText
    private lateinit var saveChoreButton: Button
    private lateinit var cancelChoreButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_chore)

        choreNameEditText = findViewById(R.id.chore_name)
        saveChoreButton = findViewById(R.id.save_chore_button)
        cancelChoreButton = findViewById(R.id.cancel_chore_button)

        saveChoreButton.setOnClickListener {
            onSaveChoreClicked()
        }

        cancelChoreButton.setOnClickListener {
            onCancelClicked()
        }
    }

    private fun onSaveChoreClicked() {

        val choreName = choreNameEditText.text.toString().trim()

        if (TextUtils.isEmpty(choreName)) {
            Toast.makeText(this, "Please enter a chore name", Toast.LENGTH_SHORT).show()
        } else {
            addChoreToList()
            Toast.makeText(this, "Chore Added Successfully", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun onCancelClicked() {
        finish()
    }

    private fun addChoreToList() {

        // Logic needed to add chore to list or parent profile
    }
}