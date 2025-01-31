package com.example.househeroesv2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class ChoreScreen : Fragment(R.layout.chore_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.choice1Image).setOnClickListener {
            openVideoTutorial("https://www.example.com/laundry_tutorial") // Example URL
        }
    }

    private fun openVideoTutorial(url: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
        startActivity(intent)
    }
}
