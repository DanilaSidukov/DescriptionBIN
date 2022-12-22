package com.sidukov.descriptionbin

import android.graphics.Paint
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class InformationActivity : AppCompatActivity() {

    private lateinit var textBINNumber: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        textBINNumber = findViewById(R.id.bin_number)
        textBINNumber.paintFlags = Paint.UNDERLINE_TEXT_FLAG

    }

}