package com.example.househeroesv2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.househeroesv2.databinding.ActivityParentPasscodeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class SetPasscodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityParentPasscodeBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Bind the layout
        binding = ActivityParentPasscodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.submitPasscodeButton.setOnClickListener {
            val passcode = binding.passcodeInput.text.toString()

            if (passcode.isEmpty() || passcode.length < 4) {
                Toast.makeText(this, "Passcode must be at least 4 digits", Toast.LENGTH_SHORT).show()
            } else {
                savePasscodeToFirestore(passcode)
            }
        }
    }

    private fun savePasscodeToFirestore(passcode: String) {
        val currentUser = auth.currentUser

        if (currentUser != null) {
            val userId = currentUser.uid
            val passcodeData: Map<String, Any> = mapOf("passcode" to passcode)

            db.collection("Users").document(userId)
                .set(passcodeData, SetOptions.merge())
                .addOnSuccessListener {
                    Toast.makeText(this, "Passcode set successfully!", Toast.LENGTH_SHORT).show()
                    navigateToParentPortal()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun navigateToParentPortal() {
        val intent = Intent(this, ParentActivity::class.java)
        startActivity(intent)
        finish()
    }
}
