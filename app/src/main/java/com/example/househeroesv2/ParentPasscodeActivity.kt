package com.example.househeroesv2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.househeroesv2.databinding.ActivityParentPasscodeBinding

class ParentPasscodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityParentPasscodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Bind the layout
        binding = ActivityParentPasscodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitPasscodeButton.setOnClickListener {
            val passcode = binding.passcodeInput.text.toString()

            if (passcode == "1234") {
                val intent = Intent(this, ParentActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Incorrect Passcode", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

