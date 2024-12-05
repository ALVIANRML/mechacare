package com.example.mechacare

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class start : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_start)
        val textView = findViewById<TextView>(R.id.tvstart)

        val myView = findViewById<View>(R.id.main)
        myView?.setOnApplyWindowInsetsListener { view, insets ->
            insets
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, activity_register::class.java)
            startActivity(intent)
            finish()
        }, 500)

        val spannable = SpannableString("MechaCare")
        spannable.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.yellow)), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) // Mecha
        spannable.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.white)), 5, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) // nya

        textView.text = spannable
}}