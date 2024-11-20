package com.example.mechacare

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_button_start : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_button_start)
        val textView = findViewById<TextView>(R.id.tvlogo)

        val spannable = SpannableString("MechaCare")
        spannable.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.yellow)), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) // Mecha
        spannable.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.white)), 5, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) // nya

        textView.text = spannable
    }
}