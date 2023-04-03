package com.nigel.marks.wizsquared

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.nigel.marks.wizsquared.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Initialisiere Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        //Setze die View
        setContentView(view)

        //Refresh DB
        binding.refreshDbButton.setOnClickListener{
            viewModel.loadSpells()
            Toast.makeText(this,"Data updated.",Toast.LENGTH_SHORT).show()
        }
    }
}