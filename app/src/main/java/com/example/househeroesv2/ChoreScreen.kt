package com.example.househeroesv2

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class ChoreScreen : Fragment(R.layout.chore_screen) {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    @SuppressLint("SetTextI18n", "DefaultLocale")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val checkBox1 = view.findViewById<CheckBox>(R.id.checkBox1)
        val checkBox2 = view.findViewById<CheckBox>(R.id.checkBox2)
        val checkBox3 = view.findViewById<CheckBox>(R.id.checkBox3)
        val checkBox4 = view.findViewById<CheckBox>(R.id.checkBox4)

        val coinTextView = view.findViewById<TextView>(R.id.textView2)

        // Video tutorial buttons
        val chore1VideoButton = view.findViewById<Button>(R.id.chore1VideoButton)
        val chore2VideoButton = view.findViewById<Button>(R.id.chore2VideoButton)
        val chore3VideoButton = view.findViewById<Button>(R.id.chore3VideoButton)
        val chore4VideoButton = view.findViewById<Button>(R.id.chore4VideoButton)

        // Observe coin count and update display
        sharedViewModel.choreCoins.observe(viewLifecycleOwner) { coinCount ->
            coinTextView.text = "ChoreCoin: ${String.format("%05d", coinCount)}"
        }

        // Helper function to add coins when a chore is completed
        fun completeTask(message: String) {
            sharedViewModel.addCoins(10)
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

        // Checkbox listeners
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

        // heres for uploaded videos
        chore1VideoButton.setOnClickListener {// do Laundry
            openVideoTutorial("https://streamable.com/eovg4a")
        }

        chore2VideoButton.setOnClickListener {// sweep
            openVideoTutorial("https://www.youtube.com/watch?v=xvFZjo5PgG0")
        }

        chore3VideoButton.setOnClickListener {// take out trash
            openVideoTutorial("https://www.example.com/video3.mp4")
        }

        chore4VideoButton.setOnClickListener { //Wash dishes
            openVideoTutorial("https://www.example.com/video4.mp4")
        }
    }

    private fun openVideoTutorial(url: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
        startActivity(intent)
    }
}
