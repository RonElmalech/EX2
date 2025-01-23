package com.example.assigment2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.CheckBox
import androidx.activity.ComponentActivity
import androidx.appcompat.widget.Toolbar
import com.example.assigment2.model.Student

class StudentDetailsActivity : ComponentActivity() {
    private lateinit var student: Student
    private lateinit var studentId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        studentId = intent.getStringExtra("studentId")!! // Get the student ID from the intent
        fetchStudentData(studentId) // Fetch student data from the server

        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            onBackPressed()
        }
        findViewById<TextView>(R.id.toolbarTitle).text = "Student Details" // Set title
        backButton.visibility = View.VISIBLE

        findViewById<Button>(R.id.edit_button).setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("student", student)
            startActivityForResult(intent, StudentsListActivity.EDIT_STUDENT_REQUEST)
        }
    }

    private fun fetchStudentData(studentId: String) {
        // Fetch the student data based on the ID
        student = (application as MyApp).getStudents().find { it.id == studentId } ?: run {
            finish() // Finish the activity if the student is not found
            return
        }

        // Update the UI with the student data
        findViewById<ImageView>(R.id.student_pic).setImageResource(R.drawable.ic_student)
        findViewById<TextView>(R.id.student_name).text = "name: ${student.name}"
        findViewById<TextView>(R.id.student_id).text = "id: ${student.id}"
        findViewById<TextView>(R.id.student_phone).text = "phone: ${student.phone}"
        findViewById<TextView>(R.id.student_address).text = "address: ${student.address}"

        val studentCheckbox: CheckBox = findViewById(R.id.student_checkbox)
        // Set the checkbox state and disable it to prevent user interaction
        studentCheckbox.isChecked = student.isChecked
        studentCheckbox.text = if (student.isChecked) "checked" else "not checked"
        studentCheckbox.isEnabled = false // Disable the checkbox to prevent changes
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == StudentsListActivity.EDIT_STUDENT_REQUEST && resultCode == RESULT_OK) {
            val updatedStudent = data?.getSerializableExtra("student") as Student // Get the updated student
            (application as MyApp).updateStudent(updatedStudent, studentId) // Update the student in MyApp
            student = updatedStudent // Update the local student reference
            fetchStudentData(updatedStudent.id) // Reload the student data
        }
    }
}