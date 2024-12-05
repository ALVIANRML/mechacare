package com.example.mechacare

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class EditProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val radioGroup: RadioGroup = findViewById(R.id.tv_jenis_kelamin)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton: RadioButton = findViewById(checkedId)
            val selectedText = selectedRadioButton.text.toString()

            // Show selected option in a Toast message
            Toast.makeText(this, "Selected: $selectedText", Toast.LENGTH_SHORT).show()
    }
        val btnSelectDate = findViewById<Button>(R.id.tv_tanggal_lahir)

        // Set OnClickListener pada tombol
        btnSelectDate.setOnClickListener {
            showDatePickerDialog()
        }

}
    private fun showDatePickerDialog() {
        // Mendapatkan tanggal saat ini
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Menampilkan DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Tanggal yang dipilih
                val selectedDate = "${selectedMonth + 1}/$selectedDay/$selectedYear"
                Toast.makeText(this, "Selected date: $selectedDate", Toast.LENGTH_SHORT).show()
            },
            year, month, day
        )
        datePickerDialog.show()
    }
}