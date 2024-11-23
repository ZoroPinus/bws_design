package com.example.bws_design.Auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bws_design.R

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val backButton = findViewById<TextView>(R.id.back_button)
        val signInNav= findViewById<TextView>(R.id.sign_in_nav)
        val spinner = findViewById<Spinner>(R.id.marital_status_input)
        val maritalStatusOptions = listOf("Select Marital Status", "Single", "Married", "Divorced", "Widowed")


        val adapter = ArrayAdapter(this, R.layout.marital_spinner_item, maritalStatusOptions)
        adapter.setDropDownViewResource(R.layout.marital_status_dropdown_item) // Use the custom dropdown layout
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (position == 0) {
                    // Hint selected
                } else {
                    val selectedStatus = maritalStatusOptions[position]
                    print(selectedStatus)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Optional: Handle no selection case
            }
        }
        backButton.setOnClickListener {
            finish()
        }
        signInNav.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

    }
}