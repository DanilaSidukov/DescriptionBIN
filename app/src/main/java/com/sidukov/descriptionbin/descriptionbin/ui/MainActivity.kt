package com.sidukov.descriptionbin.descriptionbin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.sidukov.descriptionbin.databinding.ActivityMainBinding
import com.sidukov.descriptionbin.descriptionbin.data.CardBINRepository
import com.sidukov.descriptionbin.descriptionbin.data.remote.APIClient
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var cardBINViewModel: CardBINViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bind()

        cardBINViewModel = CardBINViewModel(
            CardBINRepository(
                APIClient.binApiClient
            )
        )

        val textBIN = Intent(this@MainActivity, InformationActivity::class.java)

        lifecycleScope.launch {
            cardBINViewModel.dataBIN.collect { DataBIN ->
                println(DataBIN)
                if (DataBIN != null){
                    runOnUiThread {
                        textBIN.putExtra ("dataBIN", DataBIN)
                        textBIN.putExtra("textBIN", getFusedNumberBIN(binding.editInputBin.text.toString()))
                        startActivity(textBIN)
                    }
                }
            }
        }
        lifecycleScope.launch{
            cardBINViewModel.error.collect{ errorString ->
                runOnUiThread {
                    println(errorString)
                    Toast.makeText(this@MainActivity, "Error: $errorString\nInput another BIN", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.buttonGetInformation.setOnClickListener {
            if (binding.editInputBin.text.isNotEmpty()) {
                cardBINViewModel.requestBinData(getFusedNumberBIN(binding.editInputBin.text.toString()))
            } else {
                Toast.makeText(this, "Error: Input BIN of card", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonShowHistory.setOnClickListener {
            val historyActivity = Intent(this, HistoryActivity::class.java)
            historyActivity.putExtra("openHistory", 1)
            startActivity(historyActivity)
        }
    }

    private fun getFusedNumberBIN(str: String): String {
        return str.filterNot { it == ' ' }
    }

    private fun bind() = with(binding){
        val numberOrder = MaskedTextChangedListener("[0000]{ }[0000]{ }[0000]{ }[0000]", editInputBin)
        editInputBin.addTextChangedListener(numberOrder)
        editInputBin.onFocusChangeListener = numberOrder
    }
}

