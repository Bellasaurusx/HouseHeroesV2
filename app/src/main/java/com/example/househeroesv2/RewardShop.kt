package com.example.househeroesv2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class RewardShop : Fragment(R.layout.reward_shop) {
    private val sharedViewModel: SharedViewModel by activityViewModels()

    @SuppressLint("SetTextI18n", "DefaultLocale")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coinDisplay = view.findViewById<TextView>(R.id.coinDisplay)


        sharedViewModel.choreCoins.observe(viewLifecycleOwner) { coinCount ->
            coinDisplay.text = "ChoreCoin: ${String.format("%05d", coinCount)}"
        }
    }
}