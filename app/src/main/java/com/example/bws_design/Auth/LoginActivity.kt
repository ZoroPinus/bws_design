package com.example.bws_design.Auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bws_design.Dashboard.DashboardActivity
import com.example.bws_design.R

class LoginActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val passwordInput = findViewById<EditText>(R.id.password_input)
        val signUpNav = findViewById<TextView>(R.id.sign_up_nav)
        val signInButton = findViewById<Button>(R.id.sign_in_button)
        var isPasswordVisible = false

        passwordInput.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableEnd = passwordInput.compoundDrawables[2]
                if (drawableEnd != null && event.rawX >= (passwordInput.right - drawableEnd.bounds.width())) {
                    isPasswordVisible = !isPasswordVisible
                    passwordInput.inputType = if (isPasswordVisible) {
                        InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    } else {
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    }
                    passwordInput.setSelection(passwordInput.text.length)
                    val drawable = if (isPasswordVisible) {
                        ContextCompat.getDrawable(this, R.drawable.ic_pass_show)
                    } else {
                        ContextCompat.getDrawable(this, R.drawable.ic_pass_hidden)
                    }
                    passwordInput.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
                    return@setOnTouchListener true
                }
            }
            false
        }
        signUpNav.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        signInButton.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }
    }
}