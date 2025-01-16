package com.example.househeroesv2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class RewardShop : Fragment(R.layout.reward_shop) {

    private val sharedViewModel: SharedViewModel by activityViewModels() // If using a ViewModel
    private lateinit var coinDisplay: TextView

    @SuppressLint("SetTextI18n", "DefaultLocale")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coinDisplay = view.findViewById(R.id.coinDisplay)

        sharedViewModel.choreCoins.observe(/* owner = */ viewLifecycleOwner) { coinCount ->
            coinDisplay.text = "ChoreCoins ${String.format("%05d", coinCount)}"
        }

        val choice1Image = view.findViewById<ImageView>(R.id.choice1Image)
        val choice1CostText = view.findViewById<TextView>(R.id.choice1Cost)

        val choice2Image = view.findViewById<ImageView>(R.id.choice2Image)
        val choice2CostText = view.findViewById<TextView>(R.id.choice2Cost)

        val choice3Image = view.findViewById<ImageView>(R.id.choice3Image)
        val choice3CostText = view.findViewById<TextView>(R.id.choice3Cost)

        val choice4Image = view.findViewById<ImageView>(R.id.choice4Image)
        val choice4CostText = view.findViewById<TextView>(R.id.choice4Cost)

        // Function to handle reward purchase
        fun purchaseReward(costText: TextView) {
            val costString = costText.text.toString().replace(" coins", "")
            val cost = costString.toIntOrNull() ?: return

            val currentCoins = sharedViewModel.choreCoins.value ?: 0
            if (currentCoins >= cost) {
                sharedViewModel.setCoins(currentCoins - cost)
                Toast.makeText(requireContext(), "Reward purchased!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Not enough coins!", Toast.LENGTH_SHORT).show()
            }
        }

        choice1Image.setOnClickListener { purchaseReward(choice1CostText) }
        choice2Image.setOnClickListener { purchaseReward(choice2CostText) }
        choice3Image.setOnClickListener { purchaseReward(choice3CostText) }
        choice4Image.setOnClickListener { purchaseReward(choice4CostText) }
    }
}
