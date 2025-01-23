package com.example.assigment2

import android.app.Application
import com.example.assigment2.model.Student

class MyApp : Application() {
    private val students = mutableListOf<Student>()

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun removeStudent(student: Student) {
        students.remove(student)
    }

    fun getStudents(): List<Student> = students

    fun updateStudent(updatedStudent: Student, originalId: String) {
        val index = students.indexOfFirst { it.id == originalId }
        if (index != -1) {
            students[index] = updatedStudent
        }
    }
} 