package com.example.calci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var calctxt:TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calctxt = findViewById(R.id.calctxt)
    }

    fun onDigit(view: View) {
        calctxt?.append("1")
    }


}