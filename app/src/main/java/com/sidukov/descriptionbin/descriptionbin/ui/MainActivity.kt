package com.sidukov.descriptionbin.descriptionbin.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.sidukov.descriptionbin.databinding.ActivityMainBinding
import com.sidukov.descriptionbin.descriptionbin.BinApplication
import com.sidukov.descriptionbin.descriptionbin.data.CardBinRepository
import com.sidukov.descriptionbin.descriptionbin.data.remote.APIClient
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var cardBINViewModel: CardBinViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bind()

        cardBINViewModel = CardBinViewModel(
            CardBinRepository(
                APIClient.binApiClient,
                BinApplication.database.daoHistoryBin()
            )
        )

        val textBIN = Intent(this@MainActivity, InformationActivity::class.java)

        lifecycleScope.launch {
            cardBINViewModel.dataBin.collect { dataBIN ->
                if (dataBIN != null) {
                    runOnUiThread {
                        textBIN.putExtra("dataBIN", dataBIN)
                        textBIN.putExtra(
                            "textBIN",
                            getFusedNumberBin(binding.editInputBin.text.toString())
                        )
                        startActivity(textBIN)
                    }
                }
            }
        }
        lifecycleScope.launch {
            cardBINViewModel.error.collect { errorString ->
                runOnUiThread {
                    Toast.makeText(
                        this@MainActivity,
                        "Error: Provided BIN was not found!\nTry to input another BIN",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.buttonGetInformation.setOnClickListener {
            if (binding.editInputBin.text.isNotEmpty()) {
                cardBINViewModel.requestBinData(getFusedNumberBin(binding.editInputBin.text.toString()))
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

    private fun getFusedNumberBin(str: String): String {
        return str.filterNot { it == ' ' }
    }

    private fun bind() = with(binding) {
        val numberOrder =
            MaskedTextChangedListener("[0000]{ }[0000]{ }[0000]{ }[0000]", editInputBin)
        editInputBin.addTextChangedListener(numberOrder)
        editInputBin.onFocusChangeListener = numberOrder
    }
}

