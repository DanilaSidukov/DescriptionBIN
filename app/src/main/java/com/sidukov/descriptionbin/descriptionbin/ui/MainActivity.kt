package com.sidukov.descriptionbin.descriptionbin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.sidukov.descriptionbin.R
import com.sidukov.descriptionbin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bind()

        binding.buttonGetInformation.setOnClickListener{
            if (binding.editInputBin.text.isNotEmpty()){
                getFusedNumberBIN(binding.editInputBin.text.toString())
                val textBIN = Intent(this, InformationActivity::class.java)
                textBIN.putExtra("textBIN", getFusedNumberBIN(binding.editInputBin.text.toString()))
                startActivity(textBIN)
            } else {
                Toast.makeText(this@MainActivity, "Can't provide information", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getFusedNumberBIN(str: String) : String{
        println("STRING BEFORE = $str")
        val fusedStr = str.filterNot { it == ' ' }
        println(" STRING = $fusedStr")
        return fusedStr
    }

    private fun bind() = with(binding){
        val numberOrder = MaskedTextChangedListener("[0000]{ }[0000]", editInputBin)
        editInputBin.addTextChangedListener(numberOrder)
        editInputBin.onFocusChangeListener = numberOrder
    }
}

