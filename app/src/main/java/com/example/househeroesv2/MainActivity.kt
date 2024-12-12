package com.example.househeroesv2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.househeroesv2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding // added for button binding

    //these  vars are to define the buttons, gotta look it up still step1
    private lateinit var checkBox1 : CheckBox //declare a variable that will be initialized later but not null
    private lateinit var checkBox2 : CheckBox
    private lateinit var checkBox3 : CheckBox
    private lateinit var checkBox4 : CheckBox
    private lateinit var subButt : Button




    override fun onCreate(savedInstanceState: Bundle?) //oh this is an onCreate...
    {
        super.onCreate(savedInstanceState)//keep this

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkBox1 = findViewById(R.id.checkBox1)// not sure what it does step2
        checkBox2 = findViewById(R.id.checkBox2)
        checkBox3 = findViewById(R.id.checkBox3)
        checkBox4 = findViewById(R.id.checkBox4)
        //subButt = findViewById(R.id.subButt)

        setupCheckBoxListeners()

        binding.frag1Button.setOnClickListener { // parent frag
            replaceFragment(ParentFragment())
        }
        binding.frag2Button.setOnClickListener { // for kid button
            replaceFragment(KidFragment())

        }

    }

    private fun replaceFragment(fragment: Fragment) { //idk a bunch of code XD
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()

    }

    override fun onClick(v: View)// this function is for onClicks for buttons
    {
        when(v.id){
            R.id.checkBox1 -> {}
        }
    }
    private fun setupCheckBoxListeners() // this is for checkboxes
    {
        checkBox1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
            {//toast is for pop up messages
                Toast.makeText(this, "Congratulations! First task Completed", Toast.LENGTH_SHORT).show()
            }
        }
        checkBox2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                Toast.makeText(this, "Congratulations! Second task Completed", Toast.LENGTH_SHORT).show()
            }
        }
        checkBox3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                Toast.makeText(this, "Congratulations! Third task Completed", Toast.LENGTH_SHORT).show()
            }
        }
        checkBox4.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                Toast.makeText(this, "Congratulations! last task Completed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
