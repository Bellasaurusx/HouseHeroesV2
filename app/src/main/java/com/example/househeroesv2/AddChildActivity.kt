package com.example.househeroesv2

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Button
import android.widget.Toast

class AddChildActivity : AppCompatActivity() {

    private lateinit var childNameEditText: EditText
    private lateinit var saveChildButton: Button
    private lateinit var cancelChildButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_child)

        childNameEditText = findViewById(R.id.child_name)
        saveChildButton = findViewById(R.id.save_child_button)
        cancelChildButton = findViewById(R.id.cancel_child_button)

        saveChildButton.setOnClickListener {
            onSaveChildClicked()
        }

        cancelChildButton.setOnClickListener {
            onCancelClicked()
        }
    }

    private fun onSaveChildClicked() {

        val childName = childNameEditText.text.toString().trim()

        if (TextUtils.isEmpty(childName)) {
            Toast.makeText(this, "Please enter child name", Toast.LENGTH_LONG).show()
        } else {

            addChildToParent()

            Toast.makeText(this, "Child added successfully", Toast.LENGTH_LONG).show()

            finish()
        }
    }

    private fun onCancelClicked() {

        finish()
    }

    private fun addChildToParent() {

        // Logic needed to add child to parent profile
        // For now, I will display a success message

        Toast.makeText(this, "Child added successfully", Toast.LENGTH_LONG).show()
    }
}