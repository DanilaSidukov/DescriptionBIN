package com.sidukov.descriptionbin.descriptionbin.ui

import android.graphics.Paint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sidukov.descriptionbin.databinding.ActivityInformationBinding
import com.sidukov.descriptionbin.descriptionbin.data.CardBINRepository
import com.sidukov.descriptionbin.descriptionbin.data.getBooleanString
import com.sidukov.descriptionbin.descriptionbin.data.remote.APIClient
import kotlinx.coroutines.launch
import java.util.*

class InformationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInformationBinding

    private lateinit var cardBINViewModel: CardBINViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bind()

        val argumentsBIN = intent.getStringExtra("textBIN")
        println("GET STRING = $argumentsBIN")

        cardBINViewModel = CardBINViewModel(
            CardBINRepository(
                APIClient.binApiClient,
                argumentsBIN.toString()
            )
        )

        lifecycleScope.launch {
            cardBINViewModel.dataBIN.collect { uiDataBIN ->
                if (uiDataBIN.isEmpty()) return@collect
                binding.binNumber.text = argumentsBIN.toString()
                binding.scheme.text = uiDataBIN[0].scheme.capitalize(Locale.ROOT)
                binding.type.text = uiDataBIN[0].type.capitalize(Locale.ROOT)
                binding.brand.text = uiDataBIN[0].brand
                binding.prepaid.text = uiDataBIN[0].prepaid
                binding.length.text = uiDataBIN[0].number.length.toString()
                binding.lunh.text = getBooleanString(uiDataBIN[0].number.luhn.toString())
                binding.country.text = uiDataBIN[0].country.emoji + " ${uiDataBIN[0].country.name}"
                binding.coordinates.text =
                    "(latitude: ${uiDataBIN[0].country.latitude}, longitude: ${uiDataBIN[0].country.longitude})"
                binding.bankName.text = "${uiDataBIN[0].bank.name}"
                binding.bankSite.text = uiDataBIN[0].bank.url
                binding.bankNumber.text = uiDataBIN[0].bank.phone
            }
        }

    }

    private fun bind() = with(binding) {
        binNumber.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }

}