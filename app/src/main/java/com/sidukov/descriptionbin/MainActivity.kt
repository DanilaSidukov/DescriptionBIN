package com.sidukov.descriptionbin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import org.intellij.lang.annotations.JdkConstants.InputEventMask

class MainActivity : AppCompatActivity() {

    private lateinit var inputBIN: EditText
    private lateinit var buttonGetInformation: Button
    private lateinit var buttonShowHistory: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputBIN = findViewById(R.id.edit_input_bin)
        buttonGetInformation = findViewById(R.id.button_get_information)
        buttonShowHistory = findViewById(R.id.button_show_history)

//        val numberOrder = MaskedTextChangedListener("[0000]-[0000]", inputBIN)

        buttonGetInformation.setOnClickListener{
            if (inputBIN.text.isNotEmpty()){
                val informationIntent = Intent(this@MainActivity, InformationActivity::class.java)
                informationIntent.putExtra("second", 1)
                startActivity(informationIntent)
            } else {
                Toast.makeText(this@MainActivity, "Can't provide information", Toast.LENGTH_SHORT).show()
            }
        }
    }
}