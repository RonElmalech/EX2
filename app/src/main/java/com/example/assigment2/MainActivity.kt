package com.example.assigment2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import com.example.assigment2.ui.theme.Assigment2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Assigment2Theme {
                // Use LaunchedEffect to navigate after the composition is set up
                LaunchedEffect(Unit) {
                    startActivity(Intent(this@MainActivity, StudentsListActivity::class.java))
                    finish() // Call finish to prevent returning to MainActivity
                }
            }
        }
    }
}