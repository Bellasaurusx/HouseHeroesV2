package com.example.househeroesv2

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels // gotta import pages...
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.househeroesv2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val sharedViewModel: SharedViewModel by viewModels() // added for the coins

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //these are for the buttons that are pressed
        binding.button1.setOnClickListener {
            replaceFragment(ChoreScreen())
        }
        binding.button2.setOnClickListener {
            replaceFragment(RewardShop())
        }
        binding.button3.setOnClickListener {
            replaceFragment(ParentSettings())
        }
        binding.button4.setOnClickListener {
            replaceFragment(HighScores())
        }
        binding.button5.setOnClickListener {
            replaceFragment(ProfileSettings())
        }
        binding.button10.setOnClickListener {
            //Launch ParentDashboardScreen as an activity
            val intent = Intent(this, ParentDashboardScreen::class.java)
            startActivity(intent)
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()

        // this is to hide the reward shop fragment
        if (fragment is RewardShop || fragment is ParentSettings || fragment is HighScores) {
            binding.button2.visibility = View.GONE
        } else {
            binding.button2.visibility = View.VISIBLE
        }
    }
}
