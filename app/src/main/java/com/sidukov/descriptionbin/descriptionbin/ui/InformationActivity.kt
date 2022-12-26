package com.sidukov.descriptionbin.descriptionbin.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sidukov.descriptionbin.R
import com.sidukov.descriptionbin.databinding.ActivityInformationBinding
import com.sidukov.descriptionbin.descriptionbin.domain.DataBin
import com.sidukov.descriptionbin.descriptionbin.getSplitStringBIN
import com.sidukov.descriptionbin.descriptionbin.toResponseString
import java.util.*

class InformationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInformationBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val argumentsTextBIN = intent.getStringExtra("textBIN")
        val argumentsDataBIN =
            (if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU)
                intent.getSerializableExtra("dataBIN")
            else
                intent.getSerializableExtra("dataBIN", DataBin::class.java)) as DataBin

        binding.binNumber.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        binding.binNumber.text = getSplitStringBIN(argumentsTextBIN.toString())
        binding.scheme.text = argumentsDataBIN.scheme.capitalize(Locale.ROOT) ?: "No Data"
        binding.type.text =
            if (argumentsDataBIN.type != null) argumentsDataBIN.type.capitalize(Locale.ROOT) else "No Data"
        binding.brand.text = argumentsDataBIN.brand ?: "No Data"
        binding.prepaid.text = argumentsDataBIN.prepaid.toResponseString() ?: "No Data"
        binding.length.text = argumentsDataBIN.number.length.toString() ?: "No Data"
        binding.lunh.text = argumentsDataBIN.number.luhn.toResponseString() ?: "No Data"
        binding.country.text =
            argumentsDataBIN.country.emoji + " ${argumentsDataBIN.country.name}" ?: "No Data"
        binding.coordinates.text =
            "(latitude: ${argumentsDataBIN.country.latitude}, longitude: ${argumentsDataBIN.country.longitude})"
                ?: "No Data"
        binding.bankName.text =
            if (argumentsDataBIN.bank.name != null) "${argumentsDataBIN.bank.name}" else "No Data"
        binding.bankSite.text = argumentsDataBIN.bank.url ?: "No Data"
        binding.bankNumber.text = argumentsDataBIN.bank.phone ?: "No Data"

        setBlueColorText(binding.bankNumber)
        setBlueColorText(binding.bankSite)
        setBlueColorText(binding.coordinates)

        binding.imageBack.setOnClickListener {
            finish()
        }

        binding.apply {
            bankNumber.setOnClickListener {
                if (bankNumber.text != "No Data") {
                    val intentCall = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${bankNumber.text}"))
                    startActivity(intentCall)
                }
            }
        }


        binding.apply {
            bankSite.setOnClickListener {
                if (bankSite.text != "No Data") {
                    if (!bankSite.text.startsWith("http://") && !bankSite.text.startsWith("https://")) {
                        val bankBrowser = "http://${bankSite.text}"
                        val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse(bankBrowser))
                        startActivity(intentBrowser)
                    } else {
                        val intentBrowser =
                            Intent(Intent.ACTION_VIEW, Uri.parse("${bankSite.text}"))
                        startActivity(intentBrowser)
                    }
                }
            }

        }

        val geoCoordinates = String.format(
            Locale.ENGLISH,
            "geo:%f,%f",
            argumentsDataBIN.country.latitude.toFloat(),
            argumentsDataBIN.country.longitude.toFloat()
        )

        binding.apply {
            coordinates.setOnClickListener {
                if (coordinates.text != "No Data") {
                    val intentMap = Intent(Intent.ACTION_VIEW, Uri.parse(geoCoordinates))
                    startActivity(intentMap)
                }
            }
        }
    }
}

@SuppressLint("ResourceAsColor")
private fun setBlueColorText(text: TextView) {
    if (text.text != "No Data") {
        text.setTextColor(R.color.blue)
        text.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }
}