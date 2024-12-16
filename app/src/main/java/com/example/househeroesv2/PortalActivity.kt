package com.example.househeroesv2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.househeroesv2.databinding.ActivityPortalBinding

class PortalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPortalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPortalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.parentPortalButton.setOnClickListener {
            val intent = Intent(this, ParentPasscodeActivity::class.java)
            startActivity(intent)
        }

        binding.childPortalButton.setOnClickListener {
            val intent = Intent(this, ChildActivity::class.java)
            startActivity(intent)
        }
    }
}
