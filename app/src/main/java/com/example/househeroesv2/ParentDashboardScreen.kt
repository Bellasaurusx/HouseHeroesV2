package com.example.househeroesv2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView

class ParentDashboardScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_dashboard)

        val profilePicture: ImageView = findViewById(R.id.profile_picture)
        val userName: TextView = findViewById(R.id.user_name)

        userName.text = getString(R.string.parent_name)
        profilePicture.setImageResource(R.drawable.default_profile_picture)

        val addChoreButton: Button = findViewById(R.id.add_chore_button)
        addChoreButton.setOnClickListener {
            val intent = Intent(this, AddChoreActivity::class.java)
            startActivity(intent)
        }

        val addChildButton: Button = findViewById(R.id.add_child_button)
        addChildButton.setOnClickListener {
            val intent = Intent(this, AddChildActivity::class.java)
            startActivity(intent)
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val navigationView: NavigationView = findViewById(R.id.navigation_view)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(android.R.drawable.ic_menu_more)

        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.change_password_button -> {
                    startActivity(Intent(this, ChangePasswordActivity::class.java))
                }
                R.id.delete_account_button -> {
                    startActivity(Intent(this, DeleteAccountActivity::class.java))
                }
                R.id.log_out_button -> {
                    startActivity(Intent(this, LogOutActivity::class.java))
                }
            }
            drawerLayout.closeDrawers()
            true
        }
    }
}