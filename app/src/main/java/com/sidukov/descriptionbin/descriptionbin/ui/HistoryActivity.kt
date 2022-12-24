package com.sidukov.descriptionbin.descriptionbin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sidukov.descriptionbin.R
import com.sidukov.descriptionbin.databinding.ActivityHistoryBinding

class HistoryActivity: AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_history)





    }
}