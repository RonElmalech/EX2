package com.example.assigment2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assigment2.R
import com.example.assigment2.model.Student

class StudentAdapter(
    private val context: Context,
    private var students: List<Student>,
    private val onItemClick: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.student_name)
        val idTextView: TextView = itemView.findViewById(R.id.student_id)
        val checkBox: CheckBox = itemView.findViewById(R.id.student_checkbox)

        fun bind(student: Student) {
            nameTextView.text = student.name
            idTextView.text = student.id
            checkBox.isChecked = student.isChecked

            checkBox.setOnCheckedChangeListener { _, isChecked ->
                student.isChecked = isChecked
            }

            itemView.setOnClickListener { onItemClick(student) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students[position])
    }

    override fun getItemCount(): Int = students.size

} 