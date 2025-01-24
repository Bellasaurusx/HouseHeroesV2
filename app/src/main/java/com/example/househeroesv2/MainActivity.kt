package com.example.househeroesv2

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.househeroesv2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val sharedViewModel: SharedViewModel by viewModels() // Added for the coins

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up)


        val buttonList = listOf(
            binding.button1, binding.button2,
            binding.button4, binding.button5, binding.button10,
            binding.backButton
        )

        buttonList.forEach { button ->
            button.startAnimation(fadeIn)
        }


        binding.splashLogo.startAnimation(scaleUp)


        binding.button1.setOnClickListener {
            replaceFragment(ChoreScreen())
        }
        binding.button2.setOnClickListener {
            replaceFragment(RewardShop())
        }
        binding.button4.setOnClickListener {
            replaceFragment(HighScores())
        }
        binding.button5.setOnClickListener {
            replaceFragment(ProfileSettings())
        }
        binding.button10.setOnClickListener {
            replaceFragment(ParentDashboardScreen())
        }
        binding.backButton.setOnClickListener {
            replaceFragment(RewardShop())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()

        // Hide button2 for specific fragments
        if (fragment is RewardShop || fragment is ParentSettings || fragment is HighScores) {
            binding.button2.visibility = View.GONE
        } else {
            binding.button2.visibility = View.VISIBLE
        }
    }
}
