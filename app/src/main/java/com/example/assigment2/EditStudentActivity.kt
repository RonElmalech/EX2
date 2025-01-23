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

class EditStudentActivity : ComponentActivity() {
    private lateinit var student: Student
    private lateinit var originalId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)
        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            onBackPressed()
        }
        student = intent.getSerializableExtra("student") as Student
        originalId = student.id
        findViewById<TextView>(R.id.toolbarTitle).text = "Edit Student"
        val nameEditText: EditText = findViewById(R.id.name_edit_text)
        val idEditText: EditText = findViewById(R.id.id_edit_text)
        val phoneEditText: EditText = findViewById(R.id.phone_edit_text)
        val addressEditText: EditText = findViewById(R.id.address_edit_text)
        val studentCheckbox: CheckBox = findViewById(R.id.student_checkbox)

        nameEditText.setText(student.name)
        idEditText.setText(student.id)
        phoneEditText.setText(student.phone)
        addressEditText.setText(student.address)
        studentCheckbox.isChecked = student.isChecked
        studentCheckbox.text = if (student.isChecked) "checked" else "not checked"

        studentCheckbox.setOnCheckedChangeListener { _, isChecked ->
            student.isChecked = isChecked
            studentCheckbox.text = if (isChecked) "checked" else "not checked"
        }

        findViewById<Button>(R.id.save_button).setOnClickListener {
            student.name = nameEditText.text.toString()
            student.id = idEditText.text.toString()
            student.phone = phoneEditText.text.toString()
            student.address = addressEditText.text.toString()
            student.isChecked = studentCheckbox.isChecked

            val app = application as MyApp
            app.updateStudent(student, originalId)

            val resultIntent = Intent()
            resultIntent.putExtra("student", student)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        findViewById<Button>(R.id.delete_button).setOnClickListener {
            (application as MyApp).removeStudent(student)
            val intent = Intent(this, StudentsListActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Add Cancel button functionality
        findViewById<Button>(R.id.cancel_button).setOnClickListener {
            finish() // Just finish the activity to go back to the details
        }
    }
} 