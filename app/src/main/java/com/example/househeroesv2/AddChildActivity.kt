package com.example.househeroesv2

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
// works
class AddChildActivity : AppCompatActivity() {

    private lateinit var childNameEditText: EditText
    private lateinit var saveChildButton: Button
    private lateinit var cancelChildButton: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_child)

        childNameEditText = findViewById(R.id.child_name)
        saveChildButton = findViewById(R.id.save_child_button)
        cancelChildButton = findViewById(R.id.cancel_child_button)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

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

            addChildToFirestore(childName)
        }
    }

    private fun onCancelClicked() {

        finish()
    }

    private fun addChildToFirestore(childName: String) {
        val currentUser = auth.currentUser

        if (currentUser != null) {
            val userId = currentUser.uid
            val childData = hashMapOf(
                "name" to childName
            )

            // Add to Firestore
            db.collection("Users").document(userId).collection("Children")
                .add(childData) // Generates a unique ID for each child
                .addOnSuccessListener {
                    Toast.makeText(this, "Child added successfully!", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show()
        }
    }
}
