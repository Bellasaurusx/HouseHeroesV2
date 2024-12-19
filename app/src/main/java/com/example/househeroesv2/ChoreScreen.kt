package com.example.househeroesv2

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels// import page

class ChoreScreen : Fragment(R.layout.chore_screen) {
    private val sharedViewModel: SharedViewModel by activityViewModels()// this is for coins

    private lateinit var coinTextView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val checkBox1 = view.findViewById<CheckBox>(R.id.checkBox1) // these are gfor the check boxes
        val checkBox2 = view.findViewById<CheckBox>(R.id.checkBox2)
        val checkBox3 = view.findViewById<CheckBox>(R.id.checkBox3)
        val checkBox4 = view.findViewById<CheckBox>(R.id.checkBox4)
        val coinTextView = view.findViewById<TextView>(R.id.textView2)

        // for the format of the coins
        sharedViewModel.choreCoins.observe(viewLifecycleOwner) { coinCount ->
            coinTextView.text = "ChoreCoin: ${String.format("%05d", coinCount)}"
        }
        // coin counter stuff
        fun completeTask(message: String) {
            sharedViewModel.addCoins(10) // adds money to coins
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

        checkBox1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                completeTask("Congratulations! First task Completed")
            }
        }

        checkBox2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                completeTask("Congratulations! Second task Completed")
            }
        }

        checkBox3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                completeTask("Congratulations! Third task Completed")
            }
        }

        checkBox4.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                completeTask("Congratulations! Last task Completed")
            }
        }
    }
}