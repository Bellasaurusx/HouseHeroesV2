package com.example.househeroesv2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity

class ParentDashboardScreen : Fragment(R.layout.activity_parent_dashboard) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val profilePicture: ImageView = view.findViewById(R.id.profile_picture)
        val userName: TextView = view.findViewById(R.id.user_name)

        userName.text = getString(R.string.parent_name)
        profilePicture.setImageResource(R.drawable.default_profile_picture)

        val addChoreButton: Button = view.findViewById(R.id.add_chore_button)
        addChoreButton.setOnClickListener {
            val intent = Intent(requireContext(), AddChoreActivity::class.java)
            startActivity(intent)
        }

        val addChildButton: Button = view.findViewById(R.id.add_child_button)
        addChildButton.setOnClickListener {
            val intent = Intent(requireContext(), AddChildActivity::class.java)
            startActivity(intent)
        }

        val changePasswordButton: Button = view.findViewById(R.id.change_password_button)
        changePasswordButton.setOnClickListener {
            val intent = Intent(requireContext(), ChangePasswordActivity::class.java)
            startActivity(intent)
        }

        val deleteAccountButton: Button = view.findViewById(R.id.delete_account_button)
        deleteAccountButton.setOnClickListener {
            val intent = Intent(requireContext(), DeleteAccountActivity::class.java)
            startActivity(intent)
        }

        val drawerLayout: DrawerLayout = view.findViewById(R.id.drawer_layout)
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)

        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            drawerLayout.open()
        }
    }
}