package com.example.househeroesv2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.househeroesv2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    //these lateinit vars are to define the buttons, gotta look it up still step1
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
                Toast.makeText(this, "Congratulations! You completed your first task!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
