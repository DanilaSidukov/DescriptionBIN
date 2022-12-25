package com.sidukov.descriptionbin.descriptionbin.ui

import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sidukov.descriptionbin.databinding.ActivityInformationBinding
import com.sidukov.descriptionbin.descriptionbin.data.toResponseString
import com.sidukov.descriptionbin.descriptionbin.domain.DataBIN
import java.util.*

class InformationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInformationBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bind()

        val argumentsTextBIN = intent.getStringExtra("textBIN")
        val argumentsDataBIN =
            (if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU)
                intent.getSerializableExtra("dataBIN")
            else
                intent.getSerializableExtra("dataBIN", DataBIN::class.java)) as DataBIN

        binding.binNumber.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        binding.binNumber.text = getSplitStringBIN(argumentsTextBIN.toString())
        binding.scheme.text = argumentsDataBIN.scheme.capitalize (Locale.ROOT) ?: "No Data"
        binding.type.text = if (argumentsDataBIN.type != null) argumentsDataBIN.type.capitalize(Locale.ROOT) else "No Data"
        binding.brand.text = argumentsDataBIN.brand ?: "No Data"
        binding.prepaid.text = argumentsDataBIN.prepaid.toResponseString()?: "No Data"
        binding.length.text = argumentsDataBIN.number.length.toString() ?: "No Data"
        binding.lunh.text = argumentsDataBIN.number.luhn.toResponseString() ?: "No Data"
        binding.country.text = argumentsDataBIN.country.emoji + " ${argumentsDataBIN.country.name}" ?: "No Data"
        binding.coordinates.text = "(latitude: ${argumentsDataBIN.country.latitude}, longitude: ${argumentsDataBIN.country.longitude})" ?: "No Data"
        binding.bankName.text = if (argumentsDataBIN.bank.name != null) "${argumentsDataBIN.bank.name}" else "No Data"
        binding.bankSite.text = argumentsDataBIN.bank.url ?: "No Data"
        binding.bankNumber.text = argumentsDataBIN.bank.phone ?: "No Data"

        binding.imageBack.setOnClickListener {
            finish()
        }
    }

    private fun bind() = with(binding) {

    }
}
fun getSplitStringBIN(stringBIN: String): String {
    var tempStringBIN = " "
    var count = 0
    val stringChar = stringBIN.toCharArray()
    for (char in stringChar) {
        if (count == 4) {
            tempStringBIN += " $char"
            count = 0
        } else {
            tempStringBIN += char
            count += 1
        }
    }
    return tempStringBIN
}

fun checkOnNull(string: String?): String{
    return string ?: "No Data"
}