package com.example.assigment2.model

import java.io.Serializable

data class Student(
    var name: String,
    var id: String,
    var phone: String,
    var address: String,
    var isChecked: Boolean = false
) : Serializable