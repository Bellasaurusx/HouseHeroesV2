package com.example.househeroesv2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.househeroesv2.databinding.ActivityParentPasscodeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ParentPasscodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityParentPasscodeBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Bind the layout
        binding = ActivityParentPasscodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.submitPasscodeButton.setOnClickListener {
            val inputPasscode = binding.passcodeInput.text.toString()

            if (inputPasscode.isEmpty()) {
                Toast.makeText(this, "Please enter the passcode", Toast.LENGTH_SHORT).show()
            } else {
                validatePasscode(inputPasscode)
            }
        }
    }

    private fun validatePasscode(inputPasscode: String) {
        val currentUser = auth.currentUser

        if (currentUser != null) {
            val userId = currentUser.uid

            db.collection("Users").document(userId).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val storedPasscode = document.getString("passcode")

                        if (storedPasscode == inputPasscode) {
                            val intent = Intent(this, ParentActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Incorrect Passcode", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "Passcode not found. Please set it up.", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}