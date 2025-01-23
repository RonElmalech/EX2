package com.example.assigment2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.assigment2.model.Student

class NewStudentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)
        findViewById<TextView>(R.id.toolbarTitle).text = "New Student"
        val nameEditText: EditText = findViewById(R.id.name_edit_text)
        val idEditText: EditText = findViewById(R.id.id_edit_text)
        val phoneEditText: EditText = findViewById(R.id.phone_edit_text)
        val addressEditText: EditText = findViewById(R.id.address_edit_text)
        val studentCheckbox: CheckBox = findViewById(R.id.student_checkbox)
        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            onBackPressed()
        }
        

        studentCheckbox.setOnCheckedChangeListener { _, isChecked ->
            studentCheckbox.text = if (isChecked) "checked" else "not checked"
        }
        findViewById<Button>(R.id.save_button).setOnClickListener {
            val student = Student(
                name = nameEditText.text.toString(),
                id = idEditText.text.toString(),
                phone = phoneEditText.text.toString(),
                address = addressEditText.text.toString(),
                isChecked = studentCheckbox.isChecked
            )
            val resultIntent = Intent()
            resultIntent.putExtra("student", student)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        // Handle the Cancel button click
        findViewById<Button>(R.id.cancel_button).setOnClickListener {
            finish() // Just finish the activity to go back
        }
    }
} 