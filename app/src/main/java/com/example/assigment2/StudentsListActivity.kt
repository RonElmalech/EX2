package com.example.assigment2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assigment2.adapter.StudentAdapter
import com.example.assigment2.model.Student
import kotlin.math.log

class StudentsListActivity : AppCompatActivity() {
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var students: List<Student>
    private lateinit var noStudentsMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_list)

        val app = application as MyApp
        students = app.getStudents() // Get the list of students from MyApp

        noStudentsMessage = findViewById(R.id.no_students_message)

        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.visibility = View.GONE
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        findViewById<TextView>(R.id.toolbarTitle).text = "Students List"

        // Check if the student list is empty
        if (students.isEmpty()) {
            noStudentsMessage.visibility = View.VISIBLE // Show the no students message
        } else {
            noStudentsMessage.visibility = View.GONE // Hide the no students message
        }

        // Set up the RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.students_recycler_view)
        studentAdapter = StudentAdapter(this, students) { student ->
            val intent = Intent(this, StudentDetailsActivity::class.java)
            intent.putExtra("studentId", student.id) // Pass only the student ID
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        // Set up the add student button
        findViewById<Button>(R.id.add_student_button).setOnClickListener {
            val intent = Intent(this, NewStudentActivity::class.java)
            startActivityForResult(intent, ADD_STUDENT_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_STUDENT_REQUEST && resultCode == RESULT_OK) {
            val student = data?.getSerializableExtra("student") as Student
            (application as MyApp).addStudent(student)
            students = (application as MyApp).getStudents() // Refresh the student list
            studentAdapter.notifyItemInserted(students.size - 1) // Notify that an item was inserted
            noStudentsMessage.visibility = View.GONE // Hide the no students message
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh the student list from the application state
        students = (application as MyApp).getStudents()

        // Check if the student list is empty
        if (students.isEmpty()) {
            noStudentsMessage.visibility = View.VISIBLE // Show the no students message
        } else {
            noStudentsMessage.visibility = View.GONE // Hide the no students message
        }

        // Notify the adapter to refresh the data
        studentAdapter.notifyDataSetChanged()
    }

    companion object {
        const val ADD_STUDENT_REQUEST = 1
        const val EDIT_STUDENT_REQUEST = 2
    }
}
